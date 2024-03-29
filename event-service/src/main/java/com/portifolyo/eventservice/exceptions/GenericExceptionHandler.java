package com.portifolyo.eventservice.exceptions;

import feign.FeignException;
import org.portifolyo.commonexceptions.NotFoundException;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(FeignException.UnprocessableEntity.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GenericResponse<Void>> notFoundUserExceptionHandler(Exception ex) {
        return ResponseEntity.badRequest().body(GenericResponse.BAD_REQUEST(ex.getMessage()));
    }

    @ExceptionHandler(GenericException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericResponse<Void>> generalExceptionHandler(GenericException ex) {
        GenericResponse<Void> response = new GenericResponse<>(ex.getStatusCode(),ex.getMessage(),null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(FeignException.FeignClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse<Void> feignClientException(FeignException.FeignClientException ex){
        return GenericResponse.BAD_REQUEST(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse<Void> notFoundException(NotFoundException ex){
        return new GenericResponse<>(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }


}
