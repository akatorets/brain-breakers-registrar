package registrar.mapper;

import registrar.exception.TokenException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TokenExceptionMapper implements ExceptionMapper<TokenException> {
    @Override
    public Response toResponse(TokenException exception) {
        return Response
                .status(500)
                .entity(exception.getMessage())
                .build();
    }
}
