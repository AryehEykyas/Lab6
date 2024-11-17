package lab6.main.requests;

import lab6.main.commands.AbstractCommand;

/**
 * Class for command execution.
 */
public class CommandRequest extends Request
{
    private final AbstractCommand command;
    public CommandRequest(AbstractCommand command)
    {
        this.command = command;
    }

    public AbstractCommand getCommand()
    {
        return command;
    }
}
