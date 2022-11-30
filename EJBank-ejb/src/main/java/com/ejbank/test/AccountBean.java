package com.ejbank.test;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class AccountBean {
    @Override
    public  getUserLastName() {
        return "Hello from EJB";
    }

    @Override
    public  getUserFirstName() {
        return "Hello from EJB";
    }
}
