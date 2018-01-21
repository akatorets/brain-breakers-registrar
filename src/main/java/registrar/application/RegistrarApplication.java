package registrar.application;

import org.glassfish.jersey.server.ResourceConfig;
import registrar.mapper.IllegalArgumentExceptionMapper;
import registrar.mapper.TokenExceptionMapper;
import registrar.resources.RegistrationResource;

public class RegistrarApplication extends ResourceConfig {
    public RegistrarApplication() {
        register(new RegistrarBinder());
        register(IllegalArgumentExceptionMapper.class);
        register(TokenExceptionMapper.class);
        register(RegistrationResource.class);
    }
}
