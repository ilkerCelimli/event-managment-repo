package com.portifolyo.userservice.enums;

import lombok.Getter;

@Getter
public enum Role {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    Role(String value) {
        this.value = value;
    }

    private String value;
}
