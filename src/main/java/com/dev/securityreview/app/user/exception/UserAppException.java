package com.dev.securityreview.app.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAppException extends RuntimeException {
    private ErrorCode errorCode; // Enum
    private String message;

    @Override
    public String toString() {
        if (message == null) return errorCode.getMessage();
        return String.format("%s, %s", errorCode.getStatus(), errorCode.getMessage());
    }
}
