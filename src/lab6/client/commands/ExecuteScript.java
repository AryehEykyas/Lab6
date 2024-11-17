package lab6.client.commands;

import lab6.client.interactors.InputInteractor;
import lab6.main.consoles.Console;
import lab6.main.consoles.FileConsole;
import lab6.main.exceptions.IncorrectCmdArgsException;
import lab6.main.interactors.Validator;

/**
 * Class that is used to describe the "execute_script file_name" command.
 * Executes commands from a file.
 */
public class ExecuteScript extends ClientCommand
{
    private static int dep = 0, maxDep = 5;

    public ExecuteScript()
    {
        super("execute_script", "выполняет команды из файла");
    }

    public static void setMaxDep(int maxDep)
    {
        ExecuteScript.maxDep = maxDep;
    }

    @Override
    public void getValidation(String[] args) throws IncorrectCmdArgsException
    {
        if (args.length != 1 || !Validator.isFile(args[0]))
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
            String fileName = args[0];
            if (dep > maxDep)
            {
                console.print("Превышена глубина рекурсии!");
                return;
            }
            console.print("Начинаем обработку файла с командами");
            dep++;
            Console fileConsole = new FileConsole(fileName);
            InputInteractor newInputInteractor = new InputInteractor(fileConsole);
            newInputInteractor.run();
            dep--;
        }
        catch (IncorrectCmdArgsException e)
        {
            console.print(e.toString());
        }
    }
}