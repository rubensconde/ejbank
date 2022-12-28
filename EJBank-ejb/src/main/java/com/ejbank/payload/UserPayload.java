package com.ejbank.payload;

public class UserPayload {
    
    private final String firstname;
    private final String lastname;
//    private final Integer age;
//
//    public PeoplePayload(String firstname, String lastname, Integer age) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.age = age;
//    }
    public UserPayload(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
//        this.age = null;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

//    public Integer getAge() {
//        return age;
//    }
}
