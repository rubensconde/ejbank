package com.ejbank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ejbank_advisor")
@DiscriminatorValue(value = "advisor")
public class AdvisorEntity extends UserEntity implements Serializable {


    @OneToMany(mappedBy = "advisor", cascade = CascadeType.ALL)
    private List<CustomerEntity> customers;

    public AdvisorEntity() {
    }


    public List<CustomerEntity> getCustomers() {
        return customers;
    }
}
