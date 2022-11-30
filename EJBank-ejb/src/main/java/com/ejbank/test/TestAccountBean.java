package com.ejbank.test;

import javax.ejb.Local;

@Local
public interface TestAccountBean {
    String getUserFirstName();
    String getUserLastName();


}
