package lab6.client.commands;

import lab6.main.commands.AbstractCommand;
import lab6.main.consoles.Console;

/**
 * Class that is used to describe a command on the client-side.
 */
public abstract class ClientCommand extends AbstractCommand
{
    protected Console console;

    protected ClientCommand(String name, String description)
    {
        super(name, description);
    }

    public void setConsole(Console console)
    {
        this.console = console;
    }

    /**
     * Executes the "exit" command.
     *
     * @param args - command's arguments.
     */
    public abstract void execute(String[] args);
}