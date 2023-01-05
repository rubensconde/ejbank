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
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.destinationUser = destinationUser;
        this.sourceUser = sourceUser;
        this.amount = amount;
        this.author = author;
        this.comment = comment;
        this.applied = state;
    }
}
