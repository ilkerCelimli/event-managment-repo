package com.example.gatewayserver.security;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Roles {

    private List<String> SUPER_ADMIN;

    public Roles(){
        SUPER_ADMIN = new ArrayList<>();
        SUPER_ADMIN.add("ROLE_ADMIN");
        SUPER_ADMIN.add("ROLE_SUPER_ADMIN");
        SUPER_ADMIN.add("ROLE_USER");

    }

    public String[] getSuperAdminRoles(){
        return SUPER_ADMIN.toArray(String[]::new);
    }

}
