package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Description of the "remove_greater {element}" command.
 * Deleting workers who are greater than the given one.
 */
public class RemoveGreater extends CommandWithWorker
{
    public RemoveGreater()
    {
        super("remove_greater", "удаляет работников, которые больше заданного");
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
        collectionInteractor.removeGreater(worker);
    }
}
