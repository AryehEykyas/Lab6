package lab6.main.requests;

import lab6.main.commands.AbstractCommand;

/**
 * Request for getting arguments' validation.
 */
public class ValidationRequest extends Request
{
    private final AbstractCommand command;

    public ValidationRequest(AbstractCommand command)
    {
        this.command = command;
    }

    public AbstractCommand getCommand()
    {
        return command;
    }
}