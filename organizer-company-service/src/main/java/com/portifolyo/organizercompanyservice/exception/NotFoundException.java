package com.portifolyo.organizercompanyservice.exception;

public class NotFoundException extends GenericException{
    public NotFoundException(String id) {
        super(String.format("id : %s not founded",id));
    }
}
