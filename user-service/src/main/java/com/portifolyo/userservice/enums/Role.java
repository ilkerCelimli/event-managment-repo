package com.portifolyo.userservice.enums;

import lombok.Getter;

@Getter
public enum Role {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_COMPANY_ADMIN("ROLE_COMPANY_ADMIN"),
    ROLE_COMPANY_USER("ROLE_COMPANY_USER");

    Role(String value) {
        this.value = value;
    }

    private String value;
}
