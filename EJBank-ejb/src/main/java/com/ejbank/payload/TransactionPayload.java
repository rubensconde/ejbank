package com.ejbank.payload;

import com.ejbank.entity.AccountEntity;
import com.ejbank.entity.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionPayload {

    private Integer id;
    private String date;
    // private Date date;
    private Integer accountFrom;
    //private AccountEntity accountFrom;
    private Integer accountTo;
    //private AccountEntity accountTo;
    private String author;
    //private UserEntity author;
    private BigDecimal amount;
    private String comment;
    private Boolean applied;
    private String destinationUser;
    //private UserEntity destinationUser;
    private String sourceUser;
    //private UserEntity sourceUser;
    public TransactionPayload(Integer id,
                              String date,
                              Integer sourceAccount,
                              Integer destinationAccount,
                              String destinationUser,
                              String sourceUser,
                              BigDecimal amount,
                              String author,
                              String comment,
                              Boolean state){
        if(destinationUser==null) {
            this.sourceUser = sourceUser;
        }
        else{
            this.destinationUser = destinationUser;
        }
        this.id = id;
        this.date = date;
        this.accountFrom = sourceAccount;
        this.accountTo = destinationAccount;
        this.amount = amount;
        this.author = author;
        this.comment = comment;
        this.applied = state;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Integer getAccountFrom() {
        return accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public String getAuthor() {
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

    public String getDestinationUser() {
        return destinationUser;
    }

    public String getSourceUser() {
        return sourceUser;
    }
}
