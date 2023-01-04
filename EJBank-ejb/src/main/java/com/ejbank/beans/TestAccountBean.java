package com.ejbank.beans;

import com.ejbank.payload.AccountPayload;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TestAccountBean {
    List<AccountPayload> getAccounts(Integer id);
   // Integer getNbCurrentTransactions(Integer id);
}
