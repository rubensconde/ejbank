package com.ejbank.payload;

import java.math.BigDecimal;
import java.util.List;

public class ListAccountPayload {
    private final List<AccountPayload> accounts;
    private final String error;
    public ListAccountPayload(List<AccountPayload> accounts) {
        this.accounts = accounts;
        this.error=null;
    }
    public ListAccountPayload(String error) {
        this.accounts = List.of();
        this.error=error;
    }

    public List<AccountPayload> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }
}
