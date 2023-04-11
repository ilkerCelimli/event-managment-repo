package com.portifolyo.userservice.exception;

import com.portifolyo.userservice.exception.apiexceptions.EmailIsExistsException;
import jakarta.mail.MessagingException;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericException {

    @ExceptionHandler(EmailIsExistsException.class)
    public ResponseEntity<String> emailIsExistsExceptionHandler(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<String> mailSenderExceptionHandler(Exception ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

/*    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }*/

}
