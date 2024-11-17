package lab6.server.commands;

import lab6.main.exceptions.IncorrectCmdArgsException;

/**
 * Команда printFieldDescendingPosition.
 * Выводит значения поля position всех элементов в порядке убывания.
 */
public class PrintFieldDescendingPosition extends ServerCommand {

    public PrintFieldDescendingPosition() {
        super("print_field_descending_position", "выводит значения поля position всех элементов в порядке убывания");
    }

    @Override
    public void getValidation(String[] args) throws IncorrectCmdArgsException {
        if (args.length != 0) {
            throw new IncorrectCmdArgsException();
        }
    }

    @Override
    public void execute(String[] args) {
        try {
            getValidation(args);
            collectionInteractor.printFieldDescendingPosition();
        } catch (IncorrectCmdArgsException e) {
            console.print(e.toString());
        }
    }
}