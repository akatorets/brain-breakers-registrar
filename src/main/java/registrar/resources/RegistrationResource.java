package registrar.resources;

import registrar.api.VitalRadioRegistrar;
import registrar.domain.RegistrationInfo;
import registrar.response.StatusResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RegistrationResource {

    @Inject
    private VitalRadioRegistrar registrar;

    @POST
    @Path("registration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(RegistrationInfo info) {
        registrar.register(info);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @GET
    @Path("status")
    public Response status() {
        return Response.status(Response.Status.OK)
                .entity(new StatusResponse("OK"))
                .build();
    }

}
