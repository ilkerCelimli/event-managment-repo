package com.example.gatewayserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecurityModel {

    private String id;
    private String ip;
    private String email;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
    private String type;


}
