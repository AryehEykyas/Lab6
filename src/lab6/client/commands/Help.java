package lab6.client.commands;

import lab6.main.commands.AbstractCommand;
import lab6.main.exceptions.IncorrectCmdArgsException;

import java.util.Arrays;

/**
 * Class that is used to describe the "help" command.
 * Prints out descriptions of all the commands.
 */
public class Help extends ClientCommand
{
    private AbstractCommand[] commands;

    public Help()
    {
        super("help", "выводит полную информацию о командах");
    }

    public void setCommands(AbstractCommand[] commands)
    {
        this.commands = commands;
    }

    @Override
    public void getValidation(String[] args) throws IncorrectCmdArgsException
    {
        if (args.length != 0)
        {
            throw new IncorrectCmdArgsException();
        }
    }

    @Override
    public void execute(String[] args)
    {
        try
        {
            getValidation(args);
            Arrays.stream(commands).forEach(command -> console.print(command.toString()));
        }
        catch (IncorrectCmdArgsException e)
        {
            console.print(e.toString());
        }
    }
}
