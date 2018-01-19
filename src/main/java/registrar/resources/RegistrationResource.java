package registrar.resources;

import registrar.api.VitalRadioRegistrar;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Singleton
@Path("registration")
public class RegistrationResource {

    @Inject
    private VitalRadioRegistrar registrar;

    @POST
    public Response register(@QueryParam("date") String date) {
        registrar.register(date);
        return Response.status(201).build();
    }

}
