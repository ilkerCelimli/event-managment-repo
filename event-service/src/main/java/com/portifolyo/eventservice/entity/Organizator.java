package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Entity
@Table(name = "organizators",indexes = {
        @Index(name = "idx_fullname",columnList = "name,surname"),
        @Index(name = "idx_email",columnList = "email")
})
public class Organizator extends BaseEntity {

    @Column(nullable = false,name = "name",length = 20)
    private String name;
    @Column(nullable = false,name = "surname",length = 20)
    private String surname;
    @Column(nullable = false,length = 16,name = "phone_number")
    private String phoneNumber;
    @Column(nullable = false,length = 32,name = "email")
    private String email;
    @Column(nullable = false,length = 11,name = "identity_number")
    private String tcNo;

    public Organizator() {}

    public Organizator(String name, String surname, String phoneNumber, String email, String tcNo) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.tcNo = tcNo;
    }
}
