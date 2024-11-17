package lab6.main.commands;

import lab6.main.classes.Worker;

import java.io.Serializable;

/**
 * Class that is used to define an abstract command.
 *
 * It contains fields and methods for server-client commands.
 */
public class CommandDescription implements Serializable
{
    protected String name; // command's name
    protected String description; // command's description

    protected String[] args; // command's arguments
    protected boolean withWorker = false; // worker's prototype
    protected Worker worker;

    public CommandDescription(String name, String description)
    {
        this.name = name;
        this.description = description;
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

    @Override
    public String toString()
    {
        return name + ": " + description;
    }
}