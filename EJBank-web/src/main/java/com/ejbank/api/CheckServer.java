package com.ejbank.api;

import com.ejbank.payload.ServerPayload;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/server")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CheckServer {

    /**
     * Check server status and return a boolean true if the method is done.
     * @return ServerPayload
     */
    @GET
    @Path("/status")
    public ServerPayload getStatus() {
        return new ServerPayload(true);
    }
}
