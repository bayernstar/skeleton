package controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class NetIDController {
    @GET
    @Path("/netid")
    public String getNetID() {
        return "ty353";
    }

}
