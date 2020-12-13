package com.coderone95.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleAlreadyExists extends  RuntimeException{


    public RoleAlreadyExists() {
        super();
    }

    public RoleAlreadyExists(String message) {
        super(message);
    }

    public RoleAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleAlreadyExists(Throwable cause) {
        super(cause);
    }

    protected RoleAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
