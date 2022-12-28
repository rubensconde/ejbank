package com.ejbank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "ejbank_transaction")
public class TransactionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column
    private BigDecimal amount;
    @Column
    private String comment;
    @Column
    private Boolean applied;
    @Column
    private Date date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
