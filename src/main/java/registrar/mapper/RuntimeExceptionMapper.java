package registrar.mapper;

import registrar.response.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException exception) {
        ErrorResponse response = new ErrorResponse(500, exception.getMessage());
        return Response
                .status(500)
                .entity(response)
                .build();
    }
}
