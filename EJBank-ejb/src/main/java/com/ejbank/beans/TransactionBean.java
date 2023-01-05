package com.ejbank.beans;


import com.ejbank.payload.ListTransactionPayload;
import com.ejbank.payload.TransactionPayload;

import javax.ejb.Local;

@Local
public interface TransactionBean {
    Integer getNotAppliedTransactions(Integer id);

    ListTransactionPayload getTransactions(Integer accountId, Integer offset, Integer userId);
}
