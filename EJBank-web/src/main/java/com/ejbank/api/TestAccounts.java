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


    /**
     * Find accounts owned by the user userId. Returns a ListAccountPayload composed with :
     *      - id, type, amount values if accounts are found for this user
     *      - error if it the account is not one of our account (if we switch user during consultation) or if the user is an advisor (advisor has no account)
     * @param id
     * @return ListAccountPayload
     */
    @GET
    @Path("/{user_id}")
    public ListAccountPayload getAccounts(@PathParam("user_id") Integer id) {
        return accountsBean.getAccounts(id);
    }

    /**
     * Find accounts attached to the user userId. Returns a ListAccountPayload composed with :
     *       - id, user, type, amount,validation values if accounts attached are found for this user
     *       - error if the user is not an advisor (advisor has attached account and not customer)
     * @param id
     * @return ListAccountPayload
     */
    @GET
    @Path("/attached/{user_id}")
    public ListAccountPayload getAttachedAccounts(@PathParam("user_id") Integer id) {
        return accountsBean.getAttachedAccounts(id);
    }

    /**
     * Find accounts owned or attached to the user userId. Returns a ListAccountPayload composed with :
     *      - id, user, type, amount values if accounts owned or attached are found for this user
     *
     * @param id
     * @return
     */
    @GET
    @Path("/all/{user_id}")
    public ListAccountPayload getAllAccounts(@PathParam("user_id") Integer id) {
        return accountsBean.getAllAccounts(id);
    }

}
