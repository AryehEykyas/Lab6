package lab6.connector;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Class that is used to receive requests from clients.
 */
public class Server
{
    private final String host;
    private final int port;
    private ServerSocketChannel ssc;

    public Server(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException
    {
        ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(host, port));
    }

    public SocketChannel getSocketChannel() throws IOException
    {
        return ssc.accept();
    }

    public Object getObject(SocketChannel socketChannel) throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois = new ObjectInputStream(socketChannel.socket().getInputStream());
        return ois.readObject();
    }

    public void writeObject(SocketChannel socketChannel, Object obj) throws IOException, ClassNotFoundException
    {
        ObjectOutputStream oos = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        oos.writeObject(obj);
    }

    public void close() throws IOException
    {
        ssc.close();
    }
}