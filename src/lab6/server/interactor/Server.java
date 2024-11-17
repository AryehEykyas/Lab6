package lab6.server.interactor;

import lab6.main.consoles.BaseConsole;
import lab6.main.consoles.Console;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Class that is used for receiving requests from client.
 */
public class Server
{
    private final String host;
    private final int port;
    private ServerSocketChannel src;
    private static final Console console = new BaseConsole();

    public Server(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException
    {
        src = ServerSocketChannel.open();
        src.configureBlocking(false);
        src.bind(new InetSocketAddress(host, port));
    }

    public SocketChannel getSocketChannel() throws IOException
    {
        return src.accept();
    }

    public Object getObject(SocketChannel socketChannel) throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois = new ObjectInputStream(socketChannel.socket().getInputStream());
        return ois.readObject();
    }

    public void writeObject(SocketChannel socketChannel, Object obj) throws IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        oos.writeObject(obj);
    }

    public void close() throws IOException
    {
        src.close();
    }
}