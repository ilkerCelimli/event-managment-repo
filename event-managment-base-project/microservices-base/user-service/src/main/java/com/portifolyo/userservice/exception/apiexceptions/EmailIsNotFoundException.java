package com.portifolyo.userservice.exception.apiexceptions;

public class EmailIsNotFoundException extends RuntimeException{
    public EmailIsNotFoundException() {
        super(Constants.emailIsNotFound);
    }
}
