package lab6.server.commands;

/**
 * Class that is used for processing a worker.
 * Checking arguments, setting a worker, command execution.
 */
public abstract class CommandWithWorker extends ServerCommand
{
    protected CommandWithWorker(String name, String description)
    {
        super(name, description);
        withWorker = true;
    }
}