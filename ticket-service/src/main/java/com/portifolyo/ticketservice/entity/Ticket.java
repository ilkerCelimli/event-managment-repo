package com.portifolyo.ticketservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    @Column(nullable = false,length = 32)
    private String name;
    @Column(nullable = false,length = 32)
    private String surname;
    @Column(nullable = false,length = 16)
    private String phoneNumber;
    @Column(nullable = false,length = 32)
    private String email;
    @Column(nullable = false,length = 11)
    private String tcNo;
    @Column(nullable = false,length = 128)
    private String eventId;
    @Column(nullable = false)
    private boolean isDeleted = false;



    public Ticket(String name, String surname, String phoneNumber, String email, String tcNo) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.tcNo = tcNo;
    }
}
