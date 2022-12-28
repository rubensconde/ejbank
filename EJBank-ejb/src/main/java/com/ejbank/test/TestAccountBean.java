package com.ejbank.test;

import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.UserPayload;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TestAccountBean {
    List<AccountPayload> getAccounts(Integer id);
   // Integer getNbCurrentTransactions(Integer id);
}
