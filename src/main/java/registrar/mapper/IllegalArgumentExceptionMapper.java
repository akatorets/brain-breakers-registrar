package registrar.mapper;

import registrar.response.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException exception) {
        ErrorResponse response = new ErrorResponse(400, exception.getMessage());
        return Response
                .status(400)
                .entity(response)
                .build();
    }
}
