package com.ejbank.api;

import com.ejbank.payload.UserPayload;
import com.ejbank.sessions.UserBean;
import com.ejbank.test.TestAccountBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TestUser {
    @EJB
    private UserBean accountBean;


    @GET
    @Path("/{user_id}")
    public UserPayload getUser(@PathParam("user_id") Integer id) {
        return accountBean.getUser(id);
    }
}
