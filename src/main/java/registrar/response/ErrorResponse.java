package registrar.response;

public class ErrorResponse {
    private final Integer status;
    private final String errorMessage;

    public ErrorResponse(Integer status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
