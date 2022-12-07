package com.ejbank.payload;

import java.math.BigDecimal;

public class AccountPayload {
    private final Integer id;
    private final String type;
    private final BigDecimal amount;

    public AccountPayload(Integer id, String type, BigDecimal amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
