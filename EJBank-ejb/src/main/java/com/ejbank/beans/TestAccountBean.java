package com.ejbank.beans;

import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.ListAccountPayload;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TestAccountBean {
    ListAccountPayload getAccounts(Integer id);
   // Integer getNbCurrentTransactions(Integer id);
}
