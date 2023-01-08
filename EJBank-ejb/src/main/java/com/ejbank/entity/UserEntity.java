package com.ejbank.entity;

import javax.persistence.*;
import java.io.Serializable;
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
    @Column(name = "firstname",nullable = false,length = 50)
    private String firstname;
    @Column(name = "lastname",nullable = false,length = 50)
    private String lastname;
    @Column(name = "type", nullable = false)
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

    public String getType() {
        return type;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }
}
