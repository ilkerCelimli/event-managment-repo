package com.portifolyo.userservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document
@Getter
@Setter
@NoArgsConstructor
public class User {

    @MongoId
    private String id;
    @Field
    private String name;
    @Field
    private String surname;
    @Field
    private String email;
    @Field
    private String password;
    @Field
    private Date birtday;
    @Field
    private boolean isActive;

    @Field
    private String activitionCode;

    public User(String name, String surname,String email,String password, Date birtday, Boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.birtday = birtday;
        this.isActive = isActive;
        this.email = email;
        this.password = password;
    }
}
