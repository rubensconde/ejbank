package com.ejbank.payload;

import com.ejbank.entity.AccountEntity;
import com.ejbank.entity.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionPayload {
    private Integer id;
    private Integer source;
    private Integer destination;
    private Integer author;
    private BigDecimal amount;
    private String comment;
    private Boolean applied;

    public TransactionPayload(Integer source, Integer destination, BigDecimal amount, Integer author) {
        this.source = source;
        this.destination = destination;
        this.author = author;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setApplied(Boolean applied) {
        this.applied = applied;
    }



//    private Date date;
}
