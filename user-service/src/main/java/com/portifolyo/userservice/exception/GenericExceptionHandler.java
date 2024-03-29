package com.portifolyo.userservice.exception;

import com.portifolyo.userservice.exception.apiexceptions.*;
import com.sun.mail.util.MailConnectException;
import jakarta.mail.MessagingException;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(EmailIsExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericResponse<Void>> emailIsExistsExceptionHandler(Exception ex) {
        return ResponseEntity.badRequest().body(GenericResponse.BAD_REQUEST(ex.getMessage()));
    }

    @ExceptionHandler(MessagingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> mailSenderExceptionHandler(Exception ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(BannedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<GenericResponse<Void>> bannedUserException(BannedUserException bannedUserException){
        return new ResponseEntity<>(GenericResponse.BAD_REQUEST(bannedUserException.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordNotMatchesException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<GenericResponse<Void>> passwordIsNotMatches(PasswordNotMatchesException ex){
        return new ResponseEntity<>(GenericResponse.BAD_REQUEST(ex.getMessage()),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MailConnectException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public GenericResponse<Void> mailConnectExceptionHandler(MailConnectException ex) {
        return GenericResponse.SUCCESS();
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GenericResponse<Void> invalidRefreshTokenException(InvalidRefreshTokenException ex){
        return new GenericResponse<>(HttpStatus.UNAUTHORIZED.value(),ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse<Void> notfoundException(NotFoundException ex){
        return new GenericResponse<>(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }

    @ExceptionHandler(EmailIsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse<Void> emailIsNotFoundException(EmailIsNotFoundException ex){
        return new GenericResponse<>(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }
}
