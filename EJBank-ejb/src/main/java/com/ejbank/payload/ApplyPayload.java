package com.ejbank.payload;

public class ApplyPayload {
    private Boolean result;
    private String message;
    private String error;
    public ApplyPayload(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }
    public ApplyPayload(Boolean result, String message,String error) {
        this.result = result;
        this.message = message;
        this.error = error;
    }
    public ApplyPayload(String error) {
        this.error = error;
    }

    public Boolean getResult() { return result; }

    public String getMessage() { return message; }

    public String getError() { return error; }
}
