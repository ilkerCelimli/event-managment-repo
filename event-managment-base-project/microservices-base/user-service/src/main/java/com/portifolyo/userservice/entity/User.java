package com.portifolyo.userservice.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.time.LocalDateTime;

@Document
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@SuperBuilder
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
    private LocalDateTime birtday;
    @Field
    private boolean isActive;

    @Field
    private String activitionCode;

    public User(String name, String surname, String email, String password, LocalDateTime birtday, Boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.birtday = birtday;
        this.isActive = isActive;
        this.email = email;
        this.password = password;
    }
}
