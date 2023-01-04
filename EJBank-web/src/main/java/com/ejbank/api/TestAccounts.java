package com.ejbank.api;


import com.ejbank.payload.AccountPayload;
import com.ejbank.beans.TestAccountBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TestAccounts {

    @EJB
    private TestAccountBean accountBean;


    @GET
    @Path("/{user_id}")
    public List<AccountPayload> getAccounts(@PathParam("user_id") Integer id) {
        return accountBean.getAccounts(id);
    }

//    @GET
    //@Path("/transaction/validation/notification/{user_id}")
  //  public PeoplePayload getCurrentTransactions(@PathParam("user_id") Integer id) {
//        PeoplePayload user = new PeoplePayload(accountBean.getUserFirstName(),accountBean.getUserLastName());
//        return user;
 //   }
}
