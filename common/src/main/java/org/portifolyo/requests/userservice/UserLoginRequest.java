package org.portifolyo.requests.userservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserLoginRequest(
        @NotNull(message = "Email is not null")
        @NotBlank(message = "Email is Not blank")
        String email,
        @NotNull(message = "Password is not null")
        @NotBlank(message = "Passoword is Not blank")
        String password
) {
}
