package com.bci.exercise.user.exception;

import com.bci.exercise.user.config.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception ex) {


        ErrorResponse errorResponse = new ErrorResponse(new Timestamp(System.currentTimeMillis()), HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error interno en el servidor. Por favor, inténtelo de nuevo más tarde.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
