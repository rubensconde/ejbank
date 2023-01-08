package com.ejbank.payload;

import java.util.List;

public class ListTransactionPayload {
    private Integer total;
    private List<TransactionPayloadForList> transactions;
    private String error;
    public ListTransactionPayload(List<TransactionPayloadForList> transactions, Integer total) {
        this.total = total;
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

    public List<TransactionPayloadForList> getTransactions() {
        return transactions;
    }

    public String getError() {
        return error;
    }
}
