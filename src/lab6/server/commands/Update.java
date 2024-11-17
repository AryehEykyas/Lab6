package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;
import lab6.main.exceptions.IncorrectIdInputException;
import lab6.main.interactors.Validator;

/**
 * Description of the "update" command.
 * Updating worker by id based on the given one.
 */
public class Update extends CommandWithWorker
{
    public Update()
    {
        super("update", "обновляет работника по id на основе заданного работника");
    }

    @Override
    public void getValidation(String[] args) throws IncorrectCmdArgsException
    {
        if (args.length != 1 || !Validator.isLong(args[0]))
        {
            throw new IncorrectCmdArgsException();
        }
    }

    @Override
    public void serverValidateArgs(String[] args) throws IncorrectIdInputException, IncorrectCmdArgsException
    {
        getValidation(args);
        if (!collectionInteractor.existsId(Long.parseLong(args[0])))
        {
            throw new IncorrectIdInputException();
        }
    }

    @Override
    public void execute(String[] args)
    {
        try
        {
            getValidation(args);
            serverValidateArgs(args);
            collectionInteractor.update(Long.parseLong(args[0]), worker);
        }
        catch (IncorrectCmdArgsException | IncorrectIdInputException e)
        {
            console.print(e.toString());
        }
    }
}