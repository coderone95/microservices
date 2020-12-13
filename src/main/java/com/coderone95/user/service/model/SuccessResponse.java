package com.coderone95.user.service.model;

import java.io.Serializable;

public class SuccessResponse {
    private String message;
    private Status status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        status.setStatus("SUCCESS");
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SuccessResponse(){}
    public SuccessResponse(String message){
        this.message = message;
    }
}
