package com.portifolyo.userservice.service;

import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.exception.apiexceptions.EmailIsExistsException;
import com.portifolyo.userservice.exception.apiexceptions.EmailIsNotFoundException;
import com.portifolyo.userservice.repository.UserRepository;
import com.portifolyo.userservice.util.converter.UserRegisterRequestConverter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.portifolyo.requests.userservice.UserInfo;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return user.isPresent() ? user.get(): null;
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
}
