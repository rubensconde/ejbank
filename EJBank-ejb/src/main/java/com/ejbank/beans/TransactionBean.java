package com.ejbank.beans;


import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.TransactionPayload;
import com.ejbank.payload.ValidationPayload;

import javax.ejb.Local;

@Local
public interface TransactionBean {
    Integer getNotAppliedTransactions(Integer id);

    ValidationPayload transactionDetails(TransactionPayload transaction);

    ValidationPayload applyTransaction(TransactionPayload transaction);
}
