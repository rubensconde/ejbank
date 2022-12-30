package com.ejbank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ejbank_account")
public class AccountEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountTypeEntity type;
    @Column(name = "balance")
    private BigDecimal balance;
    @OneToMany(mappedBy = "accountFrom", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactionsFrom;
    @OneToMany(mappedBy = "accountTo", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactionsTo;

    public AccountEntity() {
    }


    public Integer getId() {
        return id;
    }

    public AccountTypeEntity getType() {
        return type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public List<TransactionEntity> getTransactionsFrom() {
        return transactionsFrom;
    }

    public List<TransactionEntity> getTransactionsTo() {
        return transactionsTo;
    }
}
