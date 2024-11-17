package lab6.server.commands;

import lab6.main.exceptions.DuplicateIdException;
import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Description of command "Add".
 * Adding a new worker to the collection.
 */
public class Add extends CommandWithWorker
{
    public Add()
    {
        super("add", "добавлет элемент в коллекцию");
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
            collectionInteractor.add(worker);
        }
        catch (DuplicateIdException e)
        {
            console.print(e.toString());
        }
    }
}