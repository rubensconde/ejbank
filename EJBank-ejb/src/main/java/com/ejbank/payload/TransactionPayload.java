package com.ejbank.payload;

import java.math.BigDecimal;

public class TransactionPayload {

    private Integer id;
    private Integer source;
    private Integer destination;
    private BigDecimal amount;
    private Integer author;
    private String comment;
    private Boolean applied;
    private Boolean approve;


    public TransactionPayload() {
    }
    public TransactionPayload(Integer source, Integer destination, BigDecimal amount, Integer author) {
        this.source = source;
        this.destination = destination;
        this.author = author;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getComment() { return comment;}

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setApplied(Boolean applied) {
        this.applied = applied;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }
}
