package com.ejbank.api;


import com.ejbank.api.payload.PeopleNamePayload;
import com.ejbank.test.TestAccountBean;
import com.ejbank.test.TestBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    public PeopleNamePayload getUser(Integer id) {
        PeopleNamePayload user = accountBean.ge
        return testBean.test();
    }
}
