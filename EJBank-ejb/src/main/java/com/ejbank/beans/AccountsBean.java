package com.ejbank.beans;

import com.ejbank.payload.ListAccountPayload;
import javax.ejb.Local;

@Local
public interface AccountsBean {
    ListAccountPayload getAccounts(Integer id);

    ListAccountPayload getAttachedAccounts(Integer id);

    ListAccountPayload getAllAccounts(Integer id);
}
