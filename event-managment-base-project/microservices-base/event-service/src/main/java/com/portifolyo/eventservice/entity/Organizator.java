package com.portifolyo.eventservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Entity

public class Organizator extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
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
