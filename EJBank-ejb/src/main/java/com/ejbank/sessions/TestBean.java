package com.ejbank.sessions;

import com.ejbank.beans.TestBeanLocal;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class TestBean implements TestBeanLocal {

    /**
     * Just to test the server.
     * @return String
     */
    @Override
    public String test() {
        return "Hello from EJB";
    }

}
