package com.portifolyo.userservice.exception.apiexceptions;

public class NotFoundException extends RuntimeException{


    public NotFoundException(String field) {
        super(String.format("Not found %s",field));
    }

}
