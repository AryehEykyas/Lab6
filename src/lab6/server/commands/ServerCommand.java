package lab6.server.commands;

import lab6.main.commands.AbstractCommand;
import lab6.main.consoles.Console;
import lab6.main.exceptions.IncorrectCmdArgsException;
import lab6.main.exceptions.IncorrectIdInputException;
import lab6.server.interactor.CollectionHistory;
import lab6.server.interactor.CollectionInteractor;

import java.util.LinkedList;

/**
 * Abstract class of a command that is processed on server.
 */
public abstract class ServerCommand extends AbstractCommand
{
    protected Console console;

    protected CollectionInteractor collectionInteractor;
    protected CollectionHistory collectionHistory;
    protected String dataFileName;
    protected LinkedList<ServerCommand> history;

    protected ServerCommand(String name, String description)
    {
        super(name, description);
    }

    public void setConsole(Console console)
    {
        this.console = console;
    }

    public void setCollectionInteractor(CollectionInteractor collectionInteractor)
    {
        this.collectionInteractor = collectionInteractor;
    }

    public void setCollectionHistory(CollectionHistory collectionHistory)
    {
        this.collectionHistory = collectionHistory;
    }

    public void setDataFileName(String dataFileName)
    {
        this.dataFileName = dataFileName;
    }

    public void setHistory(LinkedList<ServerCommand> history)
    {
        this.history = history;
    }

    /**
     * Checking arguments for their correctness. If arguments are incorrect, exception is thrown.
     * Checking is executed on server.
     *
     * @param args - command's arguments.
     */
    public void serverValidateArgs(String[] args) throws IncorrectIdInputException, IncorrectCmdArgsException
    {
        getValidation(args);
    }

    /**
     * Command execution.
     *
     * @param args - command's arguments.
     */
    public abstract void execute(String[] args);
}