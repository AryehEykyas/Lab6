package lab6.server.interactor;

import lab6.main.commands.AbstractCommand;
import lab6.main.consoles.StringConsole;
import lab6.main.exceptions.IncorrectCmdArgsException;
import lab6.main.exceptions.IncorrectIdInputException;
import lab6.server.commands.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Class that is used for server-side.
 * Setting all the commands.
 * Executes command, server's validation of history's saving.
 */
public class CommandInteractor
{
    private static ServerCommand[] serverCommands;

    private final static TreeMap<String, ServerCommand> strCommands = new TreeMap<>();

    private final CollectionInteractor collectionInteractor;
    private final CollectionHistory collectionHistory;
    private final String dataFileName;
    private final LinkedList<ServerCommand> history = new LinkedList<>();

    public CommandInteractor(CollectionInteractor collectionInteractor, CollectionHistory collectionHistory, String dataFileName)
    {
        this.collectionInteractor = collectionInteractor;
        this.collectionHistory = collectionHistory;
        this.dataFileName = dataFileName;

        CommandInteractor.serverCommands = new ServerCommand[]{new Info(), new Show(), new Add(),
        new Update(), new Remove(), new Clear(), new Head(), new RemoveGreater(), new History(),
        new FilterBySalary(), new PrintAscending(), new PrintFieldDescendingPosition(), new Rollback()};

        for (ServerCommand command: serverCommands)
        {
            strCommands.put(command.getName(), command);
        }
    }

    public static AbstractCommand[] getServerCommands()
    {
        return serverCommands;
    }

    public static AbstractCommand[] getAbstractCommands()
    {
        return Arrays.stream(serverCommands).map(command -> new AbstractCommand(command.getName(),
                command.getDescription(), command.isWithWorker())).toArray(AbstractCommand[]::new);
    }

    public static ServerCommand getServerCommandFromAbstractCommand(AbstractCommand command)
    {
        ServerCommand serverCommand = strCommands.get(command.getName());
        serverCommand.setArgs(command.getArgs());
        serverCommand.setWithWorker(command.isWithWorker());
        serverCommand.setWorker(command.getWorker());
        return serverCommand;
    }

    public CollectionInteractor getCollectionInteractor()
    {
        return collectionInteractor;
    }

    public CollectionHistory getCollectionHistory()
    {
        return collectionHistory;
    }

    public LinkedList<ServerCommand> getHistory()
    {
        return history;
    }

    /**
     * Processing server's validation
     *
     * @param command - concrete command
     */
    public void serverValidateCommand(ServerCommand command) throws IncorrectIdInputException, IncorrectCmdArgsException
    {
        command.setCollectionInteractor(collectionInteractor);
        command.serverValidateArgs(command.getArgs());
    }

    /**
     * Adding new state in history of collection.
     */
    public void addStateCollection()
    {
        collectionHistory.addStateCollection(collectionInteractor.getLinkedList());
    }

    /**
     * Command's execution
     *
     * @param command
     */
    public void executeCommand(ServerCommand command, StringConsole strConsole)
    {
        command.setConsole(strConsole);
        command.setHistory(getHistory());
        command.setCollectionHistory(collectionHistory);
        command.setCollectionInteractor(collectionInteractor);
        command.setDataFileName(dataFileName);

        String[] args = command.getArgs();

        command.execute(args);

        if (collectionHistory != null)
        {
            if (!(command instanceof Rollback))
            {
                addStateCollection();
            }

            history.add(command);
        }
    }
}