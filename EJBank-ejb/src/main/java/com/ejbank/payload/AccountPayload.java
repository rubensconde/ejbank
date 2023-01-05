package com.ejbank.payload;

import com.ejbank.entity.AdvisorEntity;
import com.ejbank.entity.UserEntity;

import java.math.BigDecimal;

public class AccountPayload {
    private Integer id;
    private String type;
    private BigDecimal amount;
    private String owner;
    private String advisor;
    private BigDecimal rate;


    private BigDecimal interest;
    private String error;


    public AccountPayload(Integer id, String type, BigDecimal amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }
    public AccountPayload(String owner, String advisor,BigDecimal rate,BigDecimal interest, BigDecimal amount){
        this.owner =owner;
        this.advisor = advisor;
        this.rate=rate;
        this.interest = interest;
        this.amount = amount;
    }
    public AccountPayload(String error) {
        this.error = error;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getOwner() { return owner; }

    public String getAdvisor() { return advisor; }

    public BigDecimal getInterest() { return interest; }

    public BigDecimal getRate() { return rate; }

    public String getError() { return error;}
}
