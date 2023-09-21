package com.portifolyo.userservice.util.converter;

import com.portifolyo.userservice.entity.User;
import org.portifolyo.response.UserInfo;

public class UserInfoMapper {

    public static UserInfo toDto(User u){
        return new UserInfo(u.getId(),u.getName(),u.getSurname(),u.getEmail(),u.getBirtday().toString());
    }

}
