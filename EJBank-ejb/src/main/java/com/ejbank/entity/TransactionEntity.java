package com.ejbank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ejbank_transaction")
public class TransactionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id_from", nullable = false)
    private AccountEntity accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_id_to", nullable = false)
    private AccountEntity accountTo;

    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity author;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "comment")
    private String comment;

    @Column(name = "applied", nullable = false)
    private Boolean applied;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    public TransactionEntity() {
    }

    public TransactionEntity(AccountEntity accountFrom, AccountEntity accountTo, UserEntity author, BigDecimal amount, String comment, Boolean applied, Date date) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.author = author;
        this.amount = amount;
        this.comment = comment;
        this.applied = applied;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountEntity getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(AccountEntity accountFrom) { this.accountFrom = accountFrom; }

    public AccountEntity getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(AccountEntity accountTo) { this.accountTo = accountTo; }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) { this.author = author; }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getApplied() {
        return applied;
    }

    public void setApplied(Boolean applied) {
        this.applied = applied;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
