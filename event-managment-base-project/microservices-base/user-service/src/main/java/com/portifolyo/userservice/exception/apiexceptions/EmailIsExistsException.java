package com.portifolyo.userservice.exception.apiexceptions;

import com.portifolyo.userservice.exception.Constants;

public class EmailIsExistsException extends RuntimeException{

    public EmailIsExistsException() {
        super(Constants.EMAIL_IS_EXISTS);
    }
}
