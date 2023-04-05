package com.portifolyo.userservice.service;

import com.portifolyo.userservice.customannonatations.PasswordEncoder;
import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.repository.UserRepository;
import com.portifolyo.userservice.util.converter.UserRegisterRequestConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRegisterRequestConverter userRegisterRequestConverter;
    private final UserRepository userRepository;

    public User saveUser(UserRegisterRequest userRegisterRequest) {
       User u = userRegisterRequestConverter.toEntity(userRegisterRequest);
       this.userRepository.save(u);
       log.info("saved user date {},id {}",new Date(),u.getId());
       return u;
    }

    public void activiteUser(String activitionCode) {
        Optional<User> u = this.userRepository.findUserByActivitionCode(activitionCode);
        u.ifPresent(i -> {i.setIsActive(true); this.userRepository.save(i);});
    }

}
