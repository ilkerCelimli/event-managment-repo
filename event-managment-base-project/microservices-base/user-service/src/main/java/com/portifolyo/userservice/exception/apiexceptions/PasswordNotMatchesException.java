package com.portifolyo.userservice.exception.apiexceptions;

public class PasswordNotMatchesException extends RuntimeException{

    public PasswordNotMatchesException(){
        super("password is wrong");
    }
}
