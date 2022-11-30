package com.ejbank.api.payload;

public class PeopleNamePayload {
    private final String firstname;
    private final String lastname;

    public PeoplePayload(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
