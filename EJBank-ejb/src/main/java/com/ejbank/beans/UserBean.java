package com.ejbank.beans;

import com.ejbank.payload.UserPayload;

import javax.ejb.Local;

@Local
public interface UserBean {
    UserPayload getUser(Integer id);

}
