package lab6.main.responses;

/**
 * Response for request about command's execution.
 * There is necessity in server validation since something has been changed.
 */
public class CommandResponse extends Response
{
    private boolean status;

    private String errorMessage;
    private String result;

    public boolean getStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }
}
