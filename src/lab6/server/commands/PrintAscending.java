package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Description of "print_ascending" command".
 * Printing all elements in collection in ascending order.
 */
public class PrintAscending extends ServerCommand
{
    public PrintAscending()
    {
        super("print_ascending", "выводит элементы коллекции в порядке возврастания ");
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
            collectionInteractor.printAscending();
        }
        catch (IncorrectCmdArgsException e)
        {
            console.print(e.toString());
        }
    }
}