package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Description of the command "history"
 * Printing the last 10 commands.
 */
public class History extends ServerCommand
{
    public History()
    {
        super("history", "выводит первые 11 комманд");
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
            history.stream().skip(Math.max(0, history.size() - 10)).forEach(command -> console.print(command.getName()));
        }
        catch (IncorrectCmdArgsException e)
        {
            console.print(e.toString());
        }
    }
}