package com.ejbank.api;


import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.PeoplePayload;
import com.ejbank.test.TestAccountBean;

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
    private TestAccountBean accountBean;


    @GET
    @Path("/user/{user_id}")
    public PeoplePayload getUser(@PathParam("user_id") Integer id) {
        return accountBean.getUser(id);
    }

//    @GET
//    @Path("/{user_id}")
//    public PeoplePayload getAccounts(@PathParam("user_id") Integer id) {
//        AccountPayload user = new AccountPayload(accountBean.getUserFirstName(),accountBean.getUserLastName());
//        return user;
//    }

//    @GET
    //@Path("/transaction/validation/notification/{user_id}")
  //  public PeoplePayload getCurrentTransactions(@PathParam("user_id") Integer id) {
//        PeoplePayload user = new PeoplePayload(accountBean.getUserFirstName(),accountBean.getUserLastName());
//        return user;
 //   }
}
