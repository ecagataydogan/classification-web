package com.deveso.documentclassificationapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TextValidationException.class)
    public ResponseEntity<Map<String, String>> handleTextValidationException(TextValidationException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
