package registrar.application;

import org.glassfish.jersey.server.ResourceConfig;
import registrar.resources.RegistrationResource;

public class RegistrarApplication extends ResourceConfig {
    public RegistrarApplication() {
        register(new RegistrarBinder());
        register(RegistrationResource.class);
    }
}
