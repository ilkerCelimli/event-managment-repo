package com.portifolyo.paymentservice.exception;

public class CustomSqlException extends RuntimeException{

    public CustomSqlException(){
        super("Kayıt edilemedi");
    }

}
