package org.portifolyo.requests.userservice;

public record UserLoginRequest(
        String email,
        String password
) {
}
