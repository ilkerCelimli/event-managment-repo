package com.portifolyo.userservice.exception.apiexceptions;

public class EmailIsExistsException extends RuntimeException{

    public EmailIsExistsException() {
        super(Constants.emailIsAldreadyExists);
    }
}
