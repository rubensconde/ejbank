package com.ejbank.payload;

import com.ejbank.entity.AccountEntity;
import com.ejbank.entity.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionPayload {

    private Integer id;
    private String date;
    private Integer source;
    private Integer destination;
    private String author;
    private BigDecimal amount;
    private String comment;
    private State state;
    private String destination_user;
    private String source_user;

    public static enum State {
        APPLIED, TO_APPROVE, WAITING_APPROVE
    }
    public TransactionPayload(Integer id,
                              String date,
                              Integer sourceAccount,
                              Integer destinationAccount,
                              String destinationUser,
                              String sourceUser,
                              BigDecimal amount,
                              String author,
                              String comment,
                              State state){
        if(destinationUser==null) {
            this.source_user = sourceUser;
        }
        else{
            this.destination_user = destinationUser;
        }
        this.id = id;
        this.date = date;
        this.source = sourceAccount;
        this.destination = destinationAccount;
        this.amount = amount;
        this.author = author;
        this.comment = comment;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Integer getSource() {
        return source;
    }

    public Integer getDestination() {
        return destination;
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

    public State getState() {
        return state;
    }

    public String getDestination_user() {
        return destination_user;
    }

    public String getSource_user() {
        return source_user;
    }
}
