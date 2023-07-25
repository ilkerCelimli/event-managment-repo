package org.portifolyo.requests.userservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record UserRegisterRequest (
       @NotNull(message = "Name is not null")
       @NotBlank(message = "name is not blank")
       String name,
       @NotNull(message = "Surname is not null")
       @NotBlank(message = "Surname is not blank")
       String surname,
       @NotNull(message = "Email is not null")
       @NotBlank(message = "Email is not blank")
       String email,
       @NotNull(message = "password is not null")
       @NotBlank(message = "Password is not blank")
       String password,
       LocalDateTime birtday,
       String phoneNumber,
       List<RoleRequest> role
){




}
