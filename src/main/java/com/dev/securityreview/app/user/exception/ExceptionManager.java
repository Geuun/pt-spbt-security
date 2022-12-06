package com.dev.securityreview.app.user.exception;

import com.dev.securityreview.app.user.dao.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage())); //-> RuntimeException Error Message

    }

    @ExceptionHandler(UserAppException.class)
    public ResponseEntity<?> userAppExceptionHandler(UserAppException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage())); //-> RuntimeException Error Message
    }
}
