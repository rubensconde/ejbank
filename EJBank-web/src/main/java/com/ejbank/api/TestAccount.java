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

    /**
     * Find account with accountId owned by the user userId. Returns an AccountPayload composed with :
     * - owner, advisor, rate, interest and amount values if the account is found for this user
     * - error if it the account is not one of our account (if we switch user during consultation) or if the user is an advisor (advisor has no account)
     * @param accountId
     * @param userId
     * @return AccountPayload
     */
    @GET
    @Path("/{account_id}/{user_id}")
    public AccountPayload getAccountInfo(@PathParam("account_id") Integer accountId, @PathParam("user_id") Integer userId) {
        return accountBean.getAccount(accountId,userId);
    }
}
