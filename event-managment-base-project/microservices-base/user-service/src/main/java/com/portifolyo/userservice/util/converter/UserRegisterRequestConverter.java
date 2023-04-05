package com.portifolyo.userservice.util.converter;

import com.portifolyo.userservice.customannonatations.PasswordEncoder;
import com.portifolyo.userservice.entity.User;
import org.bson.types.ObjectId;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterRequestConverter {
    @PasswordEncoder
    public User toEntity(UserRegisterRequest userRegisterRequest) {
        User u = new User(userRegisterRequest.name(), userRegisterRequest.surname(), userRegisterRequest.email(),
                userRegisterRequest.password(), userRegisterRequest.birtday(),false);
        u.setId(new ObjectId().toString());
        return u;
    }

}
