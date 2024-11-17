package lab6.server;

import lab6.main.consoles.BaseConsole;
import lab6.main.consoles.Console;
import lab6.main.interactors.Validator;
import lab6.server.interactor.ServerInteractor;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        Console console = new BaseConsole();

        String host = System.getenv("host");
        String port = System.getenv("port");
        if (host == null || port == null || !Validator.isInteger(port))
        {
            console.print("Неправильные переменные окружения");
            return;
        }

        int p = Integer.parseInt(port);

        Configuration.setHost(host);
        Configuration.setPort(p);
        Configuration.setStartFileName("test_1.json");
        Configuration.setHistoryFileName("story.json");

        ServerInteractor serverInteractor = new ServerInteractor();
        try
        {
            serverInteractor.start();
            console.print("Сервере запущен");
        }
        catch (IOException e)
        {
            console.print(e.toString());
            console.print("Не получилось запустить сервер");
            return;
        }
        serverInteractor.run();
    }
}