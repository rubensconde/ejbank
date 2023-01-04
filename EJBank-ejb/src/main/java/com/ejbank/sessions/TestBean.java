package com.ejbank.sessions;

import com.ejbank.beans.TestBeanLocal;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class TestBean implements TestBeanLocal {

    @Override
    public String test() {
        return "Hello from EJB";
    }

}
