package com.ejbank.payload;

import java.util.List;

public class ListTransactionPayload {
    private int total;
    private List<TransactionPayload> transactions;
    private String error;
    public ListTransactionPayload(List<TransactionPayload> transactions) {
        this.total = transactions.size();
        this.transactions = transactions;
        this.error=null;
    }
    public ListTransactionPayload(String error) {
        this.transactions = List.of();
        this.error=error;
    }


    public int getTotal() {
        return total;
    }

    public List<TransactionPayload> getTransactions() {
        return transactions;
    }

    public String getError() {
        return error;
    }
}
