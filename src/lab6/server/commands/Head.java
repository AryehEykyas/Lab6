package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Description of the "head" command.
 * Printing the first element.
 */
public class Head extends ServerCommand
{
    public Head()
    {
        super("head", "возвращает первый элемент коллекции");
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
            if (collectionInteractor.isEmpty())
            {
                console.print("Коллекция пустая, поэтому нет первого элемента");
            }
            else
            {
                console.print(collectionInteractor.getHead().toString());
            }
        }
        catch (IncorrectCmdArgsException e)
        {
            console.print(e.toString());
        }
    }
}