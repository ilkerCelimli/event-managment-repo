package org.portifolyo.requests.eventservice;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public record TicketRequest(
        @NotNull @NotBlank String name,
        @NotNull @NotBlank String surname,
        @NotNull @NotBlank String phoneNumber,
        @NotNull @NotBlank @Email String email,
        @NotNull @NotBlank String tcNo,
        @NotNull @NotBlank String eventId,
        @NotNull @NotBlank Date eventStartDate
        ) implements Serializable {


}
