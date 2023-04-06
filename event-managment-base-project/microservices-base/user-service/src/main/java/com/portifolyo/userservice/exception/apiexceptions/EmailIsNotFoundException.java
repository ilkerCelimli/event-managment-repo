package com.portifolyo.userservice.exception.apiexceptions;

import com.portifolyo.userservice.exception.Constants;

public class EmailIsNotFoundException extends RuntimeException{
    public EmailIsNotFoundException() {
        super(Constants.EMAIL_IS_NOT_FOUND);
    }
}
