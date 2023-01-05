package com.ejbank.api;

import com.ejbank.beans.AccountBean;
import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.ListAccountPayload;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TestAccount {
    @EJB
    private AccountBean accountBean;


    @GET
    @Path("/{account_id}/{user_id}")
    public AccountPayload getAccountInfo(@PathParam("account_id") Integer accountId, @PathParam("user_id") Integer userId) {
        return accountBean.getAccount(accountId,userId);
    }
}
