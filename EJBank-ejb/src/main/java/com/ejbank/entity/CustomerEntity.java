package com.ejbank.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "ejbank_customer")
@DiscriminatorValue(value = "customer")
public class CustomerEntity extends UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisor_id", nullable = false)
    private AdvisorEntity advisor;
    @OneToMany(mappedBy = "customer_id", cascade = CascadeType.ALL)
    private List<AccountEntity> accounts;

    public Integer getId() {
        return id;
    }
    public AdvisorEntity getAdvisor() {
        return advisor;
    }
    public List<AccountEntity> getAccounts() {
        return accounts;
    }


}
