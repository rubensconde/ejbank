package com.ejbank.beans;


import com.ejbank.payload.ApplyPayload;
import com.ejbank.payload.TransactionPayload;
import com.ejbank.payload.PreviewPayload;
import com.ejbank.payload.ValidationPayload;
import com.ejbank.payload.ListTransactionPayload;

import javax.ejb.Local;

@Local
public interface TransactionBean {
    Integer getNotAppliedTransactions(Integer id);

    PreviewPayload transactionDetails(TransactionPayload transaction);

    ApplyPayload applyTransaction(TransactionPayload transaction);

    ApplyPayload validateTransaction(ValidationPayload validation);
    ListTransactionPayload getTransactions(Integer accountId, Integer offset, Integer userId);
}
