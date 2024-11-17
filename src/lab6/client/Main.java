package lab6.client;

import lab6.client.interactors.InputInteractor;
import lab6.main.consoles.BaseConsole;
import lab6.main.consoles.Console;
import lab6.main.interactors.Validator;

public class Main
{
    public static void main(String[] args)
    {
        Console console = new BaseConsole();

        String host = System.getenv("host");
        String strPort = System.getenv("port");
        if (host == null || strPort == null || !Validator.isInteger(strPort))
        {
            console.print("Неправильные переменные окружения");
            return;
        }

        int port = Integer.parseInt(strPort);

        Configuration.setHost(host);
        Configuration.setPort(port);

        InputInteractor inputInteractor = new InputInteractor(console);

        inputInteractor.run();
    }
}