package com.ejbank.beans;


import com.ejbank.payload.ListTransactionPayload;

import javax.ejb.Local;

@Local
public interface TransactionBean {
    Integer getNotAppliedTransactions(Integer id);
    ListTransactionPayload getTransactions(Integer accountId, Integer offset, Integer userId);
}
