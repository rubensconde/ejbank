package com.ejbank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ejbank_advisor")
@DiscriminatorValue(value = "advisor")
public class AdvisorEntity extends UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "advisor_id", cascade = CascadeType.ALL)
    private List<CustomerEntity> customers;

    @Override
    public Integer getId() {
        return id;
    }
    public List<CustomerEntity> getCustomers() {
        return customers;
    }
}
