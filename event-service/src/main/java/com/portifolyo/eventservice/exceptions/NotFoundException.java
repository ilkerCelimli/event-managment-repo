package com.portifolyo.eventservice.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String id) {
        super("Not found "+id);
    }
}
