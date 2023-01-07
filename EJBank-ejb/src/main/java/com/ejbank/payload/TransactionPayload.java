package com.ejbank.payload;

import com.ejbank.entity.AccountEntity;
import com.ejbank.entity.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionPayload {

    private Integer id;
    private Date date;
    private AccountEntity accountFrom;
    private AccountEntity accountTo;
    private UserEntity author;
    private BigDecimal amount;
    private String comment;
    private Boolean applied;
    private UserEntity destinationUser;
    private UserEntity sourceUser;
    private String error;
    private int total;

    public TransactionPayload(Integer id,
                              Date date,
                              AccountEntity sourceAccount,
                              AccountEntity destinationAccount,
                              UserEntity destinationUser,
                              UserEntity sourceUser,
                              BigDecimal amount,
                              UserEntity author,
                              String comment,
                              Boolean state){
        this.id = id;
        this.date = date;
        this.accountFrom = sourceAccount;
        this.accountTo = destinationAccount;
        this.destinationUser = destinationUser;
        //this.sourceUser = sourceUser;
        this.amount = amount;
        this.author = author;
        this.comment = comment;
        this.applied = state;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public AccountEntity getAccountFrom() {
        return accountFrom;
    }

    public AccountEntity getAccountTo() {
        return accountTo;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public Boolean getApplied() {
        return applied;
    }

    public UserEntity getDestinationUser() {
        return destinationUser;
    }

    public UserEntity getSourceUser() {
        return sourceUser;
    }

    public String getError() {
        return error;
    }

    public int getTotal() {
        return total;
    }
}
