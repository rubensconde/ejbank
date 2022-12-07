package com.ejbank.test;

import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.PeoplePayload;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TestAccountBean {
    PeoplePayload getUser(Integer id);
    List<AccountPayload> getAccounts(Integer id);
    Integer getNbCurrentTransactions(Integer id);
}
