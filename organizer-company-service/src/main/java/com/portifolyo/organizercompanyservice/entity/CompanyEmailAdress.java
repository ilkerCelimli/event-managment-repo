package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_email_adresses")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEmailAdress extends BaseEntity {


    @Column(name = "host",length = 48,nullable = false)
    private String host;
    @Column(name = "email",length = 48,nullable = false)
    private String email;
    @Column(name = "password",length = 255,nullable = false)
    private String password;
    @Column(name = "port",nullable = false)
    private int port;
    @Column(name = "tts_is_required")
    private boolean ttsIsRequired;

    @OneToOne(fetch = FetchType.LAZY)
    private Company company;

    public CompanyEmailAdress(String id){
        super.setId(id);
    }
}
