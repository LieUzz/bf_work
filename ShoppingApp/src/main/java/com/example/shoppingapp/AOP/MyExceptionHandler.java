package com.example.shoppingapp.AOP;

import com.example.shoppingapp.domain.response.ErrorResponse;
import com.example.shoppingapp.exception.NotEnoughInventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = {NotEnoughInventory.class})
    public ResponseEntity<ErrorResponse> handleDemoNotFoundException(NotEnoughInventory e, WebRequest request){
        return new ResponseEntity(ErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}