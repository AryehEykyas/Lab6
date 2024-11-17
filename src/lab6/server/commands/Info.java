package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Description of the "info" command.
 * Printing out information about the collection.
 */
public class Info extends ServerCommand
{
    public Info()
    {
        super("info", "выводит информацию о коллекции");
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
            collectionInteractor.printInfo();
        }
        catch (IncorrectCmdArgsException e)
        {
            console.print(e.toString());
        }
    }
}