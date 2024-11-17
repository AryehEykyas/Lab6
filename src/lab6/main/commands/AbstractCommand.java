package lab6.main.commands;

import lab6.main.classes.Worker;
import lab6.main.exceptions.IncorrectCmdArgsException;
import lab6.main.exceptions.IncorrectIdInputException;

import java.io.Serializable;

/**
 * Class that is used to define an abstract command.
 * It contains field and methods for server-client commands.
 */
public class AbstractCommand implements Serializable
{
    protected String name; // command's name
    protected String description; // command's description

    protected String[] args; // command's arguments
    protected boolean withWorker = false; // worker's prototype
    protected Worker worker;

    public AbstractCommand(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public AbstractCommand(String name, String description, boolean withWorker)
    {
        this(name, description);
        this.withWorker = withWorker;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String[] getArgs()
    {
        return args;
    }

    public void setArgs(String[] args)
    {
        this.args = args;
    }

    public boolean isWithWorker()
    {
        return withWorker;
    }

    public void setWithWorker(boolean withWorker)
    {
        this.withWorker = withWorker;
    }

    public Worker getWorker()
    {
        return worker;
    }

    public void setWorker(Worker worker)
    {
        this.worker = worker;
    }

    /**
     * Checks the correctness of arguments.
     *
     * @param args - command's arguments.
     * @throws IncorrectCmdArgsException exception.
     * @throws IncorrectIdInputException exception.
     */
    public void getValidation(String[] args) throws IncorrectCmdArgsException, IncorrectIdInputException
    {

    }

    @Override
    public String toString()
    {
        return name + ": " + description;
    }
}