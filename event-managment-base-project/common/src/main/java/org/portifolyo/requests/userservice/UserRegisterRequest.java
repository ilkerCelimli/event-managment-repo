package org.portifolyo.requests.userservice;

import java.time.LocalDateTime;

public record UserRegisterRequest (
        String name,
        String surname,
        String email,
        String password,
        LocalDateTime birtday
){




}
