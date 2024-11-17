package lab6.server.interactor;

import lab6.main.classes.Worker;
import lab6.main.consoles.BaseConsole;
import lab6.main.consoles.Console;
import lab6.main.requests.Request;
import lab6.main.responses.Response;
import lab6.main.responses.UpdateCollectionHistoryResponses;
import lab6.server.Configuration;
import lab6.main.interactors.FileInteractor;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * Class of a server application.
 * Processing connection to client. Sends answers for client's requests.
 */
public class ServerInteractor
{
    private static final Console console = new BaseConsole();
    private final Server server;

    private final CommandInteractor commandInteractor;

    public ServerInteractor()
    {
        server = new Server(Configuration.getHost(), Configuration.getPort());
        String dataFileName = Configuration.getHistoryFileName();

        LinkedList<Worker> workers_base = JsonInteractor.getLinkedListWorkerFromStrJson(FileInteractor.readTextFromFile(dataFileName));
        CollectionInteractor collectionInteractor = new CollectionInteractor(workers_base);

        CollectionHistory collectionHistory = new CollectionHistory();
        CollectionHistory.setDataFileName(dataFileName);
        collectionHistory.setStart(workers_base);

        commandInteractor = new CommandInteractor(collectionInteractor, collectionHistory, dataFileName);
    }

    public void start() throws IOException
    {
        server.start();
    }

    public void writeRes(SocketChannel socketChannel, Response response)
    {
        try
        {
            server.writeObject(socketChannel, response);
        }
        catch (IOException e)
        {
            console.print("Не получилось передать данные клиенту");
        }
    }

    public void handlerSocketChannel(SocketChannel socketChannel) throws IOException
    {
        Request request;
        try
        {
            request = (Request) server.getObject(socketChannel);

            Response response = new RequestHandler(commandInteractor).requestHandler(request);

            if (!(response instanceof UpdateCollectionHistoryResponses))
            {
                writeRes(socketChannel, response);
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            console.print(e.toString());
            console.print("Принять данные не получилось");
            socketChannel.close();
        }
        catch (ClassCastException e)
        {
            console.print(e.toString());
            console.print("Передан неправильный тип данных.");
        }
        finally
        {
            socketChannel.close();
        }
    }

    public void run()
    {
        SocketChannel socketChannel;
        while (true)
        {
            try
            {
                socketChannel = server.getSocketChannel();
                if (socketChannel == null) continue;
                handlerSocketChannel(socketChannel);
            }
            catch (IOException e)
            {
                console.print(e.toString());
            }
        }
    }
}