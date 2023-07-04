package org.portifolyo.requests.eventservice;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

import static org.portifolyo.requests.eventservice.messages.OrganizatorRequestMessages.*;

public record OrganizatorRequest (
        @NotNull(message = ORGANIZATOR_NAME_NOT_NULL)
        @NotBlank(message = ORGANIZATOR_NAME_NOT_BLANK)
        String name,
        @NotNull(message = ORGANIZATOR_SURNAME_NOT_NULL)
        @NotBlank(message = ORGANIZATOR_SURNAME_IS_NOT_BLANK)
        String surname,
        String phoneNumber,
        @NotNull(message = EMAIL_IS_NOT_NULL)
        @Email(message = EMAIL_IS_NOT_ACCEPTABLE,regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        @NotBlank(message = EMAIL_IS_NOT_BLANK)
        String email,
        String tcNo
) implements Serializable  {
}
