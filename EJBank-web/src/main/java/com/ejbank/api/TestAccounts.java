package com.ejbank.api;


import com.ejbank.beans.AccountsBean;
import com.ejbank.payload.ListAccountPayload;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TestAccounts {

    @EJB
    private AccountsBean accountsBean;


    @GET
    @Path("/{user_id}")
    public ListAccountPayload getAccounts(@PathParam("user_id") Integer id) {
        return accountsBean.getAccounts(id);
    }

    @GET
    @Path("/attached/{user_id}")
    public ListAccountPayload getAttachedAccounts(@PathParam("user_id") Integer id) {
        return accountsBean.getAttachedAccounts(id);
    }

    @GET
    @Path("/attached/all/{user_id}")
    public ListAccountPayload getAllAccounts(@PathParam("user_id") Integer id) {
        return accountsBean.getAttachedAccounts(id);//TODO getAllAccounts
    }

}
