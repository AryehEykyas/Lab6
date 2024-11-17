package lab6.main.responses;

/**
 * Response to request about validation.
 */
public class ValidationResponse extends Response
{
    private boolean status;

    private String errorMessage;

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public boolean getStatus()
    {
        return status;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}