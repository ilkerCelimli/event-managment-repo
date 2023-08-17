package com.portifolyo.userservice.exception.apiexceptions;

public class BannedUserException extends RuntimeException{

    public BannedUserException(String email){
        super(String.format("this %s is banned",email));
    }

}
