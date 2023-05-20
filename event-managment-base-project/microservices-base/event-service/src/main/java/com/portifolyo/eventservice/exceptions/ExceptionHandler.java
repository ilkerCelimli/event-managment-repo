package com.portifolyo.eventservice.exceptions;

import feign.FeignException;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericException {

    @ExceptionHandler(FeignException.UnprocessableEntity.class)
    public ResponseEntity<String> NotFoundUserExceptionHandler(Exception ex) {
        return ResponseEntity.badRequest().body("Email Sistemde Bulunamadı");
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<GenericResponse<?>> generalExceptionHandler(GeneralException ex) {
        GenericResponse<?> response = new GenericResponse<>(ex.getStatusCode(),ex.getMessage(),null);
        return ResponseEntity.badRequest().body(response);
    }

}
