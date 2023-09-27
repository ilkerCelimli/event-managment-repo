package com.example.gatewayserver.security;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecuredEndPoints {

    protected List<String> admin;
    protected List<String> permitted;
    public SecuredEndPoints(){
        admin = new ArrayList<>();
        admin.add("/user/");
        admin.add("/user/delete-by-id/{id}");
        admin.add("/user/addrole");

        permitted = new ArrayList<>();
        permitted.add("/user/register");
        permitted.add("/user/login");
        permitted.add("/user/refresh");
    }

    public String[] getAdminEndPoints(){
        return admin.toArray(String[]::new);
    }

    public String[] getPermittedEndPoint(){
        return permitted.toArray(String[]::new);
    }

}
