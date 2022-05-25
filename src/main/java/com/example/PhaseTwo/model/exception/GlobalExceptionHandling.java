package com.example.PhaseTwo.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.InputMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> nullPointHandler(Exception ex, WebRequest request) {
        return ResponseEntity.ok(null);
    }

    @ExceptionHandler(value = InputMismatchException.class)
    public ResponseEntity<Object> inputMismatch(Exception ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> allHandler(Exception ex, WebRequest request) {
        return ResponseEntity.ok(null);
    }
}
