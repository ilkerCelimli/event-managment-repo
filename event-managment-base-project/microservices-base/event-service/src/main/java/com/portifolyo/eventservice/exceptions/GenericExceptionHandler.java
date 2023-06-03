package com.portifolyo.eventservice.exceptions;

import feign.FeignException;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(FeignException.UnprocessableEntity.class)
    public ResponseEntity<GenericResponse<Void>> NotFoundUserExceptionHandler(Exception ex) {
        return ResponseEntity.badRequest().body(GenericResponse.BAD_REQUEST());
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<GenericResponse<?>> generalExceptionHandler(GenericException ex) {
        GenericResponse<?> response = new GenericResponse<>(ex.getStatusCode(),ex.getMessage(),null);
        return ResponseEntity.badRequest().body(response);
    }

}
