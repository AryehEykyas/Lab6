package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Description of the "clear" command.
 * Clearing collection.
 */
public class Clear extends ServerCommand
{
    public Clear()
    {
        super("clear", "очищает коллекцию");
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
            collectionInteractor.clear();
        }
        catch (IncorrectCmdArgsException e)
        {
            console.print(e.toString());
        }
    }
}
