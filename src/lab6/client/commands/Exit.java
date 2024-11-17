package lab6.client.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Class that is used to describe the "exit" command.
 * Terminates the program execution.
 */
public class Exit extends ClientCommand
{
    public Exit()
    {
        super("exit", "завершает программу");
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
            System.exit(0);
        }
        catch (IncorrectCmdArgsException e)
        {
            console.print(e.toString());
        }
    }
}
