package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;
import lab6.main.exceptions.IncorrectIdInputException;
import lab6.main.interactors.Validator;

/**
 * Description of the "rollback" n times command.
 * Cancelling actions of the last n commands.
 */
public class Rollback extends ServerCommand
{
    public Rollback()
    {
        super("rollback", "отменяет действие последних n комманд");
    }

    @Override
    public void getValidation(String[] args) throws IncorrectCmdArgsException
    {
        if (args.length != 1 || !Validator.isInteger(args[0]) || Integer.parseInt(args[0]) < 0)
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
           collectionHistory.rollback(Integer.parseInt(args[0]));
           collectionInteractor.setWorkers(collectionHistory.getCurState());
       }
       catch (IncorrectCmdArgsException e)
       {
           console.print(e.toString());
       }
    }
}