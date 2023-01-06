package com.ejbank.payload;

import java.math.BigDecimal;

public class ValidationPayload {
    private Boolean result;
    private BigDecimal before;
    private BigDecimal after;
    private String message;
    private String error;


    public ValidationPayload(Boolean result, BigDecimal before, BigDecimal after, String message, String error) {
        this.result = result;
        this.before = before;
        this.after = after;
        this.message = message;
        this.error = error;
    }
    public ValidationPayload(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }
    public ValidationPayload(String error) {
        this.error = error;
    }

    public Boolean getResult() {
        return result;
    }

    public BigDecimal getBefore() {
        return before;
    }

    public BigDecimal getAfter() {
        return after;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}
