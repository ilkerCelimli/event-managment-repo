package com.portifolyo.paymentservice.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String id){
        super("Not found" + id);
    }
}
