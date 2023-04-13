package com.example.taskAppFm.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptions {
    private static final Logger logger = Logger.getLogger(Logger.class.getName());
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestEx(BadRequestException e){
        logger.info("BAD REQUEST: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleRNFE(ResourceNotFoundException e){
        logger.info("RESOURCE NOT FOUND: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
