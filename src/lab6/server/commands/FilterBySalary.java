package lab6.server.commands;


import lab6.main.exceptions.IncorrectCmdArgsException;
import lab6.main.interactors.Validator;

/**
 * Команда filter_by_salary salary.
 * Выводит работников с заданной зарплатой.
 */
public class FilterBySalary extends ServerCommand
{

    public FilterBySalary() {
        super("filter_by_salary", "выводит работников с заданной зарплатой");
    }

    @Override
    public void getValidation(String[] args) throws IncorrectCmdArgsException {
        if (args.length != 1 || !(args[0].equals("null") || Validator.isDouble(args[0]))) {
            throw new IncorrectCmdArgsException();
        }
    }

    @Override
    public void execute(String[] args) {
        try {
            getValidation(args);
            Float salary = null;  //зарплата может быть null
            if (Validator.isFloat(args[0])) {
                salary = Float.valueOf(args[0]);
            }
            collectionInteractor.getFilterBySalary(salary).forEach(worker -> console.print(worker.toString()));
        } catch (IncorrectCmdArgsException e) {
            console.print(e.toString());
        }
    }
}