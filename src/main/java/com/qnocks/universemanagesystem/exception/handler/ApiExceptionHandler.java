package com.qnocks.universemanagesystem.exception.handler;

import com.qnocks.universemanagesystem.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiException> handleResourceNotFound(ResourceNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
}
