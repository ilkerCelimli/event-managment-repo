package com.portifolyo.organizercompanyservice.util.exception;

import com.portifolyo.organizercompanyservice.exception.GenericException;
import com.portifolyo.organizercompanyservice.exception.NotFoundException;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GenericExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<GenericResponse<Void>> handleException(GenericException e) {
        return ResponseEntity.badRequest().body(GenericResponse.BAD_REQUEST(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GenericResponse<Void>> handleException(NotFoundException ex){
        return ResponseEntity.badRequest().body(
                GenericResponse.BAD_REQUEST(ex.getMessage())
        );
    }




}
