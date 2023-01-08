package com.ejbank.beans;

import com.ejbank.payload.AccountPayload;

import javax.ejb.Local;

@Local
public interface AccountBean {
    AccountPayload getAccount(Integer accountId, Integer userId);
}
