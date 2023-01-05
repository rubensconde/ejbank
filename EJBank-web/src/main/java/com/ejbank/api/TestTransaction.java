package com.ejbank.api;

import com.ejbank.beans.TransactionBean;
import com.ejbank.payload.ListAccountPayload;
import com.ejbank.payload.ListTransactionPayload;

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

    @GET
    @Path("/validation/notification/{user_id}")
    public Integer getNotAppliedTransaction(@PathParam("user_id") Integer id) {
        return transactionBean.getNotAppliedTransactions(id);
    }
    @GET
    @Path("/transaction/list/{account_id}/{offset}/{user_id}")
    public ListTransactionPayload getTransactions(@PathParam("account_id") Integer accountId,@PathParam("offset") int offset, @PathParam("user_id") Integer userId) {
        return null; //TODO
    }
    @POST
    @Path("/transaction/validation")
    public Integer validateTransaction(@PathParam("user_id") Integer id) {
        return null; //TODO
    }
}
