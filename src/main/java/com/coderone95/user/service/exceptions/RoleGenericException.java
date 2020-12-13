package com.coderone95.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleGenericException extends  RuntimeException{

    public RoleGenericException() {
        super();
    }

    public RoleGenericException(String message) {
        super(message);
    }

    public RoleGenericException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleGenericException(Throwable cause) {
        super(cause);
    }

    protected RoleGenericException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
