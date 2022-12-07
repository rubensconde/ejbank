package com.ejbank.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ejbank_customer")
public class CustomerEntity extends UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }
}
