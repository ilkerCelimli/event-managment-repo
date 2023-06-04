package com.portifolyo.userservice.exception;

import com.portifolyo.userservice.exception.apiexceptions.BannedUserException;
import com.portifolyo.userservice.exception.apiexceptions.EmailIsExistsException;
import com.portifolyo.userservice.exception.apiexceptions.PasswordNotMatchesException;
import jakarta.mail.MessagingException;
import org.apache.coyote.Response;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<GenericResponse<Void>> emailIsExistsExceptionHandler(Exception ex) {
        return ResponseEntity.badRequest().body(GenericResponse.BAD_REQUEST(ex.getMessage()));
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<String> mailSenderExceptionHandler(Exception ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(BannedUserException.class)
    public ResponseEntity<GenericResponse<Void>> bannedUserException(BannedUserException bannedUserException){
        return new ResponseEntity<>(GenericResponse.BAD_REQUEST(bannedUserException.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordNotMatchesException.class)
    public ResponseEntity<GenericResponse<Void>> passwordIsNotMatches(PasswordNotMatchesException ex){
        return new ResponseEntity<>(GenericResponse.BAD_REQUEST(ex.getMessage()),HttpStatus.FORBIDDEN);
    }

}
