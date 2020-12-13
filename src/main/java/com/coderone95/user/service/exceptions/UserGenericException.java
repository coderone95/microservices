package com.coderone95.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserGenericException extends  RuntimeException{

    public UserGenericException() {
    }

    public UserGenericException(String message) {
        super(message);
    }

    public UserGenericException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserGenericException(Throwable cause) {
        super(cause);
    }

    public UserGenericException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
