package lab6.client.interactors;

import lab6.main.commands.AbstractCommand;
import lab6.client.commands.ClientCommand;
import lab6.main.consoles.Console;
import lab6.main.exceptions.*;
import lab6.main.interactors.Validator;
import lab6.main.classes.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

/**
 * Class for working with I/O.
 */
public class InputInteractor {
    private final Console console;

    private static final String stopWorkerInput = "EXIT";  //слово, при котором обрывается ввод работника

    public InputInteractor(Console console) {
        this.console = console;
    }

    public Console getConsole() {
        return console;
    }


    public Integer getInteger(String text, boolean positive) throws IncorrectCmdArgsException, InputCompleteException {
        String tmp;  //временное хранение ввода
        int x;
        console.print(text);
        while (console.hasNext()) {
            tmp = console.getNextStr();
            if (tmp.equals(stopWorkerInput)) {
                throw new InputCompleteException();
            }
            if (lab6.main.interactors.Validator.isInteger(tmp)) {
                x = Integer.parseInt(tmp);
                if (x > 0 || (!positive)) {
                    return x;
                }
            }
            console.print(text);
        }
        throw new IncorrectCmdArgsException();
    }

    /**
     * Получает вещественное число типа Float из стандартного ввода
     *
     * @param text     текст, который подсказывает что и как вводить
     * @param notNull  true - число не может быть null, false - может
     * @param positive true - число только положительное, false - любое
     * @return Float введённое целое число (может быть null)
     */
    public Float getFloat(String text, boolean notNull, boolean positive) throws InputCompleteException, WorkerCompleteInputException {
        String tmp; //временное хранение ввода
        float x;
        console.print(text);
        while (console.hasNext()) {
            tmp = console.getNextStr();
            if (tmp.equals(stopWorkerInput)) {
                throw new InputCompleteException();
            }
            if (tmp.equals("") && !notNull) {
                return null;
            }
            if (Validator.isFloat(tmp)) {
                x = Float.parseFloat(tmp);
                if (x > 0 || (x <= 0 && !positive)) {
                    return x;
                }
            }
            console.print(text);
        }
        throw new WorkerCompleteInputException()    ;
    }

    /**
     * Получает позицию работника из стандартного ввода
     *
     * @return Position позиция работника (может быть null)
     */
    public Position getPosition() throws InputCompleteException, WorkerCompleteInputException {
        StringBuilder text = new StringBuilder("Введите позицию работника или пустую строку для null. Варианты: ");
        for (Position el : Position.values()) {
            text.append(el).append(" ");
        }

        String tmp; //временное хранение ввода
        console.print(text.toString());
        while (console.hasNext()) {
            tmp = console.getNextStr();
            if (tmp.equals(stopWorkerInput)) {
                throw new InputCompleteException();
            }
            if (tmp.equals("")) {
                return null;
            }
            try {
                return Position.valueOf(tmp);
            } catch (IllegalArgumentException e) {
                console.print(text.toString());
            }
        }
        throw new WorkerCompleteInputException();
    }

    /**
     * Получает статус работника из стандартного ввода
     *
     * @return Status статус работника (может быть null)
     */
    public Status getStatus() throws InputCompleteException, WorkerCompleteInputException {
        StringBuilder text = new StringBuilder("Введите статус работника или пустую строку для null. Варианты: ");
        for (Status el : Status.values()) {
            text.append(el).append(" ");
        }

        String tmp; //временное хранение ввода
        console.print(text.toString());
        while (console.hasNext()) {
            tmp = console.getNextStr();
            if (tmp.equals(stopWorkerInput)) {
                throw new InputCompleteException();
            }
            if (tmp.equals("")) {
                return null;
            }
            try {
                return Status.valueOf(tmp);
            } catch (IllegalArgumentException e) {
                console.print(text.toString());
            }
        }
        throw new WorkerCompleteInputException();
    }

    /**
     * Получает дату типа LocalDate (может быть null) из стандартного ввода
     *
     * @param text текст, который подсказывает что и как вводить
     * @return LocalDate введённая дата (может быть null)
     */
    public LocalDate getDate(String text) throws InputCompleteException, WorkerCompleteInputException {
        String tmp; //временное хранение ввода
        console.print(text);
        while (console.hasNext()) {
            tmp = console.getNextStr();
            if (tmp.equals(stopWorkerInput)) {
                throw new InputCompleteException();
            }
            if (tmp.equals("")) {
                return null;
            }
            try {
                return LocalDate.parse(tmp);
            } catch (DateTimeParseException e) {
                console.print(text);
            }
        }
        throw new WorkerCompleteInputException();
    }

    /**
     * Получает строку, состоящую не только из пробельных символов
     *
     * @param text      текст, который подсказывает что и как вводить
     * @param minLength минимальная допустимая длина для вводимой строки
     * @param notNull   true - число не может быть null, false - может
     * @return Float введённое целое число (может быть null)
     */
    public String getNotBlankString(String text, int minLength, boolean notNull) throws InputCompleteException, WorkerCompleteInputException {
        String tmp; //временное хранение ввода
        console.print(text);
        while (console.hasNext()) {
            tmp = console.getNextStr();
            if (tmp.equals(stopWorkerInput)) {
                throw new InputCompleteException();
            }
            if (!notNull && tmp.equals("")) {
                return null;
            }
            if (!tmp.isBlank() && tmp.length() >= minLength) {
                return tmp;
            }
            console.print(text);
        }
        throw new WorkerCompleteInputException();
    }

    /**
     * Получает данные из стандартного ввода о работнике и возвращает работника
     *
     * @return Worker - работник с введёнными полями
     */
    public Worker getWorker() throws InputCompleteException, WorkerCompleteInputException, IncorrectCmdArgsException {
        String name = getNotBlankString("Введите имя работника:", 1, true);

        console.print("Введите координаты работника (X, Y).");

        Integer x = getInteger("Введите X (целое число):", false);
        Integer y = getInteger("Введите Y (целое число):", false);

        Coordinates coordinates = new Coordinates(x, y);

        Float salary = getFloat("Введите зарплату работника (вещественное число, целую и дробную часть разделяйте точкой) или пустую строку для null:", false, true);

        Position position = getPosition();
        Status status = getStatus();

        LocalDate birthday = getDate("Введите день рождения в формате 'yyyy-mm-dd' или пустую строку для null:");

        float height = getFloat("Введите рост работника (вещественное число, целую и дробную часть разделяйте точкой):", true, true);

        String passportID = getNotBlankString("Введите id паспорта (более 6 символов) или пустую строку для null:", 7, false);

        Person person = new Person(birthday, height, passportID);

        return new Worker(name, coordinates, salary, position, status, person);

    }

    /**
     * Запускает интерактивный режим (ввод команд)
     */
    public void run() {
        ClientInteractor clientManager = new ClientInteractor();
        //получаем все команды из сервера
        AbstractCommand[] allCommands;
        try {
            allCommands = clientManager.getAllCommands();
        } catch (IOException | ClassNotFoundException e) {
            console.print(e.toString());
            console.print("Подключиться к серверу не получилось");
            return;
        }

        CommandInteractor commandInteractor = new CommandInteractor(this, allCommands);
        while (console.hasNext()) {
            String text = "Введите команду (help - чтобы узнать команды):";
            console.print(text);
            console.print("--------------------------");
            try {
                String strCommand = console.getNextStr();
                AbstractCommand command = commandInteractor.getCommand(strCommand);

                if (command instanceof ClientCommand) { //наследники ClientCommand выполняются на стороне клиента
                    ((ClientCommand) command).setConsole(console);
                    ((ClientCommand) command).execute(command.getArgs());

                    //обновляем историю состояний коллекции
                    clientManager.writeUpdateCollectionRequest();
                } else {  //остальные на сервере
                    clientManager.commandHandler(this, command);
                }
            } catch (NonExistentCommandException | IncorrectCmdArgsException | IncorrectIdInputException e) {
                console.print(e.toString());
            } catch (NoSuchElementException | InputCompleteException | WorkerCompleteInputException e) {
                console.print("");
            } catch (IOException | ClassNotFoundException e) {
                console.print(e.toString());
                console.print("При работе с сервером произошли проблемы");
            }
            console.print("--------------------------");
        }
    }
}