package lab6.main.responses;

import lab6.main.commands.AbstractCommand;

/**
 * Class that is used for processing a response to request
 * about all the commands.
 */
public class AllCommandsResponse extends Response
{
    private final AbstractCommand[] commands;

    public AllCommandsResponse(AbstractCommand[] commands)
    {
        this.commands = commands;
    }

    public AbstractCommand[] getCommands()
    {
        return commands;
    }
}