package com.example.project_java.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorDetails errorDetails = new ErrorDetails(errorCode.getCode(), errorCode.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorDetails);

        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }
}
