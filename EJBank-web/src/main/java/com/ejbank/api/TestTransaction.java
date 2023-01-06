package com.ejbank.api;

import com.ejbank.beans.TransactionBean;
import com.ejbank.payload.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TestTransaction {
    @EJB
    private TransactionBean transactionBean;

    /**
     * Find and return the number of not applied transactions.
     * @param id
     * @return Integer
     */
    @GET
    @Path("/validation/notification/{user_id}")
    public Integer getNotAppliedTransaction(@PathParam("user_id") Integer id) {
        return transactionBean.getNotAppliedTransactions(id);
    }

    /**
     * Find and return some transactions associated to the accountId and the userId according to the offset given. The ListTransactionPayload is composed with total value and a List of TransactionPayload composed with id, date, source, destination, destination_user or source_user, amount, author, optional comment and state.
     * @param accountId
     * @param offset
     * @param userId
     * @return ListTransactionPayload
     */
    @GET
    @Path("/list/{account_id}/{offset}/{user_id}")
    public ListTransactionPayload getTransactions(@PathParam("account_id") Integer accountId,@PathParam("offset") Integer offset, @PathParam("user_id") Integer userId) {
        return null;
    }

    /**
     * Post the validation of transaction
     * @return Integer
     */
    @POST
    @Path("/validation")
    public TransactionPayload validateTransaction() {
        return null; //TODO
    }

    /**
     * Post the preview of the transaction
     * @param transaction
     * @return
     */
    @POST
    @Path("/preview")
    public ValidationPayload previewTransaction(TransactionPayload transaction) {
        return transactionBean.transactionDetails(transaction);
    }

    /**
     * Post the validattion of the transaction
     * @param transaction
     * @return
     */
    @POST
    @Path("/apply")
    public ValidationPayload applyTransaction(TransactionPayload transaction) {
        return transactionBean.applyTransaction(transaction);
    }
}
