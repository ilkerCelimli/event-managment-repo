package org.portifolyo.requests.userservice;

import jakarta.validation.constraints.NotNull;

public record ForgotPasswordRequest(
      @NotNull
      String email
) {
}
