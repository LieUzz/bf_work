package com.example.login.AOP;

import com.example.login.domain.response.ErrorResponse;
import com.example.login.exception.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = {InvalidCredentialsException.class})
    public ResponseEntity<ErrorResponse> handleDemoNotFoundException(InvalidCredentialsException e, WebRequest request){
        return new ResponseEntity(ErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
