package com.portifolyo.userservice.exception.apiexceptions;

public class InvalidRefreshTokenException extends RuntimeException {

    public InvalidRefreshTokenException() {
        super(Constants.invalidRefreshToken);
    }
}
