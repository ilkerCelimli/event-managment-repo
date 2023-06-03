package com.portifolyo.userservice.service;

import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.exception.apiexceptions.BannedUserException;
import com.portifolyo.userservice.exception.apiexceptions.EmailIsExistsException;
import com.portifolyo.userservice.exception.apiexceptions.EmailIsNotFoundException;
import com.portifolyo.userservice.exception.apiexceptions.PasswordNotMatchesException;
import com.portifolyo.userservice.repository.UserRepository;
import com.portifolyo.userservice.util.JwtUtil;
import com.portifolyo.userservice.util.RandomStringGenerator;
import com.portifolyo.userservice.util.converter.UserRegisterRequestConverter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.portifolyo.requests.userservice.UserInfo;
import org.portifolyo.requests.userservice.UserLoginRequest;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.portifolyo.response.TokenResponse;
import org.portifolyo.utils.DeserializeHelper;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRegisterRequestConverter userRegisterRequestConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;

    @Transactional
    public User saveUser(UserRegisterRequest userRegisterRequest) throws MessagingException {
        if (!findEmailIsExists(userRegisterRequest.email())) {
            User u = userRegisterRequestConverter.toEntity(userRegisterRequest);
            this.userRepository.save(u);
            log.info("saved user date {},id {}", new Date(), u.getId());
            emailService.sendMail(u);
            return u;
        }
        throw new EmailIsExistsException();
    }

    @Transactional
    public void activiteUser(String activitionCode) {
        Optional<User> u = this.userRepository.findUserByActivitionCode(activitionCode);
        u.ifPresent(i -> {
            i.setIsActive(true);
            this.userRepository.save(i);
        });
    }

    @Transactional
    public void updateUser(UserRegisterRequest userRegisterRequest) {

        Optional<User> opt = this.userRepository.findUserByEmail(userRegisterRequest.email());
        opt.ifPresentOrElse((i -> {
            if (userRegisterRequest.birtday() != null) i.setBirtday(userRegisterRequest.birtday());

            if (userRegisterRequest.name() != null) i.setName(userRegisterRequest.name());

            if (userRegisterRequest.surname() != null) i.setSurname(userRegisterRequest.surname());

            if (userRegisterRequest.password() != null)
                i.setPassword(passwordEncoder.encode(userRegisterRequest.password()));

            this.userRepository.save(i);
        }), EmailIsNotFoundException::new);
    }

    public List<UserInfo> findAllUser() {
        return this.userRepository.findAllByIsActiveTrue();
    }

    public UserInfo findUserByEmail(String email) {
        Optional<UserInfo> user = this.userRepository.findUserByEmailAndIsActiveTrue(email);
        return user.orElse(null);
    }
    @Transactional
    public void handleOrganizator(OrganizatorRequest organizatorRequest) throws MessagingException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Optional<User> user = this.userRepository.findUserByEmail(organizatorRequest.email());
        if(user.isEmpty()) {
            User u = new User(organizatorRequest.name(), organizatorRequest.surname(), organizatorRequest.email(),
                    RandomStringGenerator.randomStringGenerator(),new Date(0L),true);
            this.userRepository.save(u);
            emailService.sendMail(u);
            return;
        }
        UpdateHelper<OrganizatorRequest,User> updateHelper = new UpdateHelper<>();
        this.userRepository.save(updateHelper.updateHelper(organizatorRequest,user.get()));
    }


    private boolean findEmailIsExists(String email) {
        return this.userRepository.existsUserByEmail(email);
    }

    @Transactional
    public void deleteUser(String email) {
        Optional<User> user = this.userRepository.findUserByEmail(email);
        user.ifPresentOrElse(i -> {
            i.setIsActive(false);
            this.userRepository.save(i);
        }, EmailIsNotFoundException::new);

    }

    public TokenResponse tokenResponse(UserLoginRequest userLoginRequest){
       User u = this.userRepository.findUserByEmail(userLoginRequest.email()).orElseThrow(EmailIsNotFoundException::new);
       if(!u.getIsActive()) throw new BannedUserException(userLoginRequest.email());
       if(!passwordEncoder.matches(userLoginRequest.password(),u.getPassword())) throw new PasswordNotMatchesException();
       String token = jwtUtil.generate(userLoginRequest);
       return new TokenResponse(token);
    }

    public TokenResponse tokenResponse(String token){
        String email = this.jwtUtil.validate(token).getClaim("email").asString();
        return new TokenResponse(jwtUtil.generate(email));
    }


    @RabbitListener(queues = "user-queue")
    public void handleMessage(byte[] message) throws MessagingException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        OrganizatorRequest organizatorRequest = (OrganizatorRequest) DeserializeHelper.desarialize(message);
        if(organizatorRequest != null){
            handleOrganizator(organizatorRequest);
            return;
        }
        throw new RuntimeException();
    }}
