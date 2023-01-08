package com.ejbank.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ejbank_customer")
@DiscriminatorValue(value = "customer")
public class CustomerEntity extends UserEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisor_id", nullable = false)
    private AdvisorEntity advisor;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<AccountEntity> accounts;

    public CustomerEntity() {
    }

    public AdvisorEntity getAdvisor() {
        return advisor;
    }
    public List<AccountEntity> getAccounts() {
        return accounts;
    }


}
