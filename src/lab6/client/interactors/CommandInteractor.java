package lab6.client.interactors;

import lab6.client.commands.ClientCommand;
import lab6.client.commands.ExecuteScript;
import lab6.client.commands.Exit;
import lab6.client.commands.Help;
import lab6.main.commands.AbstractCommand;
import lab6.main.consoles.BaseConsole;
import lab6.main.consoles.Console;
import lab6.main.exceptions.*;

import java.util.TreeMap;

/**
 * Class that is used for client-side.
 * Processes command from its line-description.
 */
public class CommandInteractor
{
    private final InputInteractor inputInteractor;

    private final Console console;

    private static final Help help = new Help();

    private static final AbstractCommand[] clientCommands = {new Exit(), new ExecuteScript(), help};

    private final TreeMap<String, AbstractCommand> commands = new TreeMap<>();

    public CommandInteractor(InputInteractor inputInteractor,  AbstractCommand[] allCommands)
    {
        this.inputInteractor = inputInteractor;
        console = inputInteractor.getConsole();
        for (AbstractCommand command : allCommands)
        {
            commands.put(command.getName(), command);
        }
        for (AbstractCommand command : clientCommands)
        {
            commands.put(command.getName(), command);
        }
        help.setCommands(commands.values().toArray(new AbstractCommand[0]));
    }

    /**
     * Receiving class object by its line-description.
     * If arguments are incorrect - exceptions are thrown.
     *
     * @param command - string with command
     * @return AbstractCommand - correct command
     */
    public AbstractCommand getCommand(String command) throws NonExistentCommandException, IncorrectIdInputException, IncorrectCmdArgsException, WorkerCompleteInputException, InputCompleteException {
        String[] subs = command.split("\\s+");
        command = subs[0];
        String[] args = new String[subs.length - 1];
        System.arraycopy(subs, 1, args, 0, subs.length - 1);

        if (!commands.containsKey(command))
        {
            throw new NonExistentCommandException();
        }
        AbstractCommand command1 = commands.get(commands);
        if (command1 instanceof ClientCommand)
        {
            ((ClientCommand) command1).getValidation(args);
        }
        if (command1 instanceof ExecuteScript)
        {
            if (console instanceof BaseConsole)
            {
                int maxDepth = inputInteractor.getInteger("Введите максимальную глубину рекурсии: ", true);
                ExecuteScript.setMaxDep(maxDepth);
            }
        }
        command1.setArgs(args);
        return command1;
    }
}