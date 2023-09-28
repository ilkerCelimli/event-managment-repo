package com.portifolyo.userservice.services;

import com.portifolyo.userservice.entity.Roles;
import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.enums.Role;
import com.portifolyo.userservice.exception.apiexceptions.*;
import com.portifolyo.userservice.repository.RoleRepository;
import com.portifolyo.userservice.repository.UserRepository;
import com.portifolyo.userservice.util.JwtUtil;
import com.portifolyo.userservice.util.RandomStringGenerator;
import com.portifolyo.userservice.util.converter.UserRegisterRequestConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.portifolyo.requests.userservice.UserLoginRequest;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.portifolyo.response.GenericResponse;
import org.portifolyo.response.TokenResponse;
import org.portifolyo.response.UserInfo;
import org.portifolyo.utils.DeserializeHelper;
import org.portifolyo.utils.JsonTokenUtils;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRegisterRequestConverter userRegisterRequestConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final RoleRepository roleRepository;

    @Transactional
    public User saveUser(UserRegisterRequest userRegisterRequest) {
        if (!findEmailIsExists(userRegisterRequest.email())) {
            User u = userRegisterRequestConverter.toEntity(userRegisterRequest);
            List<Roles> roles = new ArrayList<>();

            userRegisterRequest.role().forEach(i -> {
                Optional<Roles> role = this.roleRepository.findById(i.id());
                role.ifPresent(roles::add);
            });
            u.setRolesList(roles);
            this.userRepository.save(u);
            log.info("saved user date {},id {}", new Date(), u.getId());
            emailService.sendMail(u);
            return u;
        }
        throw new EmailIsExistsException();
    }

    @Transactional
    public void activiteUser(String activitionCode) {
        User u = this.userRepository.findUserByActivitionCode(activitionCode)
                .orElseThrow(() -> new NotFoundException("Activition Code"));
        u.setActive(true);
        this.userRepository.save(u);

    }

    @Transactional
    public void updateUser(UserRegisterRequest userRegisterRequest,String id) {
        User opt = this.userRepository.findUserById(id).orElseThrow(() -> new org.portifolyo.commonexceptions.NotFoundException(id));
        UpdateHelper<UserRegisterRequest, User> updateHelper = new UpdateHelper<>();
            this.userRepository.save(updateHelper.updateHelper(userRegisterRequest, opt));
    }

    public List<UserInfo> findAllUser() {
        return this.userRepository.findAllByIsActiveTrue();
    }

    public UserInfo findUserByEmail(String email) {
        Optional<UserInfo> user = this.userRepository.findUserByEmailAndIsActiveTrue(email);
        return user.orElse(null);
    }

    private boolean findEmailIsExists(String email) {
        return this.userRepository.existsUserByEmail(email);
    }

    @Transactional
    public void deleteUser(String email) {
        Optional<User> user = this.userRepository.findUserByEmail(email);
        user.ifPresentOrElse(this.userRepository::delete, EmailIsNotFoundException::new);
    }

    @Transactional
    public void deleteUserById(String id){
        Optional<User> user = this.userRepository.findUserById(id);
        user.ifPresentOrElse(ref -> {
            ref.setActive(false);
            this.userRepository.save(ref);
        }, EmailIsNotFoundException::new);
    }

    public TokenResponse tokenResponse(UserLoginRequest userLoginRequest,String ipAdress) {
        User u = this.userRepository.findUserByEmail(userLoginRequest.email()).orElseThrow(EmailIsNotFoundException::new);
        if (!u.isActive()) throw new BannedUserException(userLoginRequest.email());
        if (!passwordEncoder.matches(userLoginRequest.password(), u.getPassword()))
            throw new PasswordNotMatchesException();
        String[] list = new String[u.getRolesList().size()];
        for(int i = 0;i<list.length;i++){
            if(u.getRolesList().get(i) == null) break;
            list[i] = u.getRolesList().get(i).getRole();
        }
        String token = JsonTokenUtils.generate(userLoginRequest,list,u.getId());
        return new TokenResponse(token,JsonTokenUtils.generateRefresh(userLoginRequest.email(),list,u.getId(),ipAdress));
    }

    public TokenResponse tokenResponse(String token,String ipAdress) {
        String email = JsonTokenUtils.validate(token).getClaim("email").asString();
        String[] roles = JsonTokenUtils.validate(token).getClaim("roles").asArray(String.class);
        String ip = JsonTokenUtils.extractClaim(token,"ip",String.class);
        if(!ipAdress.equals(ip)){
            throw new InvalidRefreshTokenException();
        }
        String id = JsonTokenUtils.extractClaim(token,"id", String.class);
        return new TokenResponse(JsonTokenUtils.generate(email,roles,id,ip),JsonTokenUtils.generateRefresh(email,roles,id,ip));
    }

    public UserInfo findById(String id){
        return this.userRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new BannedUserException(id));
    }

    @Transactional
    @RabbitListener(queues = "user-queue")
    public void handleMessage(byte[] message)  {
        OrganizatorRequest organizatorRequest = DeserializeHelper.desarialize(message);
        User user = this.userRepository.findUserByEmail(organizatorRequest.email()).orElse(new User());
        if (user.getId() == null) {
            User u = new User(organizatorRequest.name(), organizatorRequest.surname(), organizatorRequest.email(),
                    RandomStringGenerator.RandomPasswordGenerator(), LocalDateTime.of(1900, 1, 1, 1, 1, 1, 1), true);
            this.userRepository.save(u);
            return;
        }
        UpdateHelper<OrganizatorRequest, User> updateHelper = new UpdateHelper<>();
        this.userRepository.save(updateHelper.updateHelper(organizatorRequest, user));
    }
}
