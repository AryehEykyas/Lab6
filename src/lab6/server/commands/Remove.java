package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Description of the "remove_by_id" command.
 * Deleting worker by its id from the collection.
 */
public class Remove extends ServerCommand {
    public Remove() {
        super("remove_by_id", "удаляет работника по d из коллекции");
    }

    @Override
    public void getValidation(String[] args) throws IncorrectCmdArgsException {
        if (args.length != 0) {
            throw new IncorrectCmdArgsException();
        }
    }

    @Override
    public void execute(String[] args)
    {
        try {
            getValidation(args);
            collectionInteractor.remove(Long.parseLong(args[0]));
        }
        catch (IncorrectCmdArgsException e)
        {
            console.print(e.toString());
        }
    }
}