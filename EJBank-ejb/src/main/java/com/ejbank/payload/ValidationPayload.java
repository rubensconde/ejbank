package com.ejbank.payload;

public class ValidationPayload {

    private Integer transaction;
    private Boolean approve;
    private Integer author;

    public ValidationPayload() {
    }

    public Integer getTransaction() { return transaction; }

    public void setTransaction(Integer transaction) { this.transaction = transaction; }

    public Boolean getApprove() { return approve; }

    public void setApprove(Boolean approve) { this.approve = approve; }

    public Integer getAuthor() { return author; }

    public void setAuthor(Integer author) { this.author = author; }

}
