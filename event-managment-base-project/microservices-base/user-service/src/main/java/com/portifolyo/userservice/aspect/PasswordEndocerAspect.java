package com.portifolyo.userservice.aspect;

import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class PasswordEndocerAspect {

    private final PasswordEncoder bCryptPasswordEncoder;

    public PasswordEndocerAspect(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    @AfterReturning(value = "@annotation(com.portifolyo.userservice.customannonatations.PasswordEncoder)",returning = "obj")
    public void encodePassword(Object obj){
       User u  =  (User) obj;
       String password = u.getPassword();
       ((User) obj).setPassword(bCryptPasswordEncoder.encode(password));
    }

}
