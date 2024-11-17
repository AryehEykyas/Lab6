package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Description of the "show" command.
 * Printing elements out.
 */
public class Show extends ServerCommand
{
    public Show()
    {
        super("show", "выводит элементы коллекции");
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
            collectionInteractor.printAllElements();
        }
        catch (IncorrectCmdArgsException e)
        {
            console.print(e.toString());
        }
    }
}