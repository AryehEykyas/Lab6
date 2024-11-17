package lab6.connector;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Class that is used to connect to the local computer.
 * Host (by default) "127.0.0.1".
 */
public class Connector
{
    private static final String host = "127.0.0.1";
    private static final int port = 5050;
    private static final ServerConfig[] servers = {new ServerConfig("127.0.0.1", 1111),
                    new ServerConfig("127.0.0.1", 1313), new ServerConfig("127.0.0.1", 6969)};

    public void start()
    {
        Server server;
        try
        {
            server = new Server(host, port);
            server.start();
        }
        catch (IOException e)
        {
            System.out.println("Не получилось запустить прослойку");
            return;
        }

        System.out.println("Прослойка запущена");
        SocketChannel socketChannel;
        while (true)
        {
            try
            {
                // receives object.
                socketChannel = server.getSocketChannel();
                if (socketChannel == null) continue;
                Object ans = server.getObject(socketChannel);

                // servers' counter.
                for (ServerConfig serverConfig : servers)
                {
                    try
                    {
                        // trying to send object to a server
                        Client client = new Client(serverConfig.getHost(), serverConfig.getPort());
                        client.start();
                        client.setObject(ans);

                        // trying to receive object from the server
                        Object response = client.getObject();
                        server.writeObject(socketChannel, response);
                        client.close();
                        break;
                    }
                    catch (IOException e)
                    {
                        continue;
                    }
                }
                socketChannel.close();
            }
            catch (IOException | ClassNotFoundException e)
            {
                System.out.println(e.toString());
            }
        }
    }
}
