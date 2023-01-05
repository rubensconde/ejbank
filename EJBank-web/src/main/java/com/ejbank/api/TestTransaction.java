package com.ejbank.api;

import com.ejbank.beans.TransactionBean;
import com.ejbank.payload.ListAccountPayload;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}
