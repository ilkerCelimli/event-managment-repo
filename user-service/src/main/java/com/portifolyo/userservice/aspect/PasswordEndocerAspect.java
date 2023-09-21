package com.portifolyo.userservice.aspect;

import com.portifolyo.userservice.entity.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PasswordEndocerAspect {

    private final PasswordEncoder bCryptPasswordEncoder;

    public PasswordEndocerAspect(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    @AfterReturning(value = "@annotation(com.portifolyo.userservice.customannonatations.PasswordEncoder)",returning = "obj")
    public void encodePassword(Object obj){
        if(obj  instanceof User user){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
    }

}
