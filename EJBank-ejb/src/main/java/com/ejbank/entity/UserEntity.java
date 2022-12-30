package com.ejbank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "ejbank_user")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name = "type")
@DiscriminatorValue(value = "none")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    private String login;
//    private String password;
//    private String email;
    @Column(name = "firstname",nullable = false,length = 50)
    private String firstname;
    @Column(name = "lastname",nullable = false,length = 50)
    private String lastname;
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;

    public UserEntity() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }
}
