package com.localWeatherAPI.applicationLayer.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> exceptionNoSuchLocationInDatabase(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
