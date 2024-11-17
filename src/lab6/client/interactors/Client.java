package lab6.client.interactors;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class that is used to send to and receive objects from server.
 */
public class Client
{
    private final String host;
    private final int port;
    private Socket socket;

    public Client(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException
    {
        socket = new Socket(host, port);
    }

    public Object getObject() throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return ois.readObject();
    }

    public void writeObject(Object obj) throws IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(obj);
    }

    public void close() throws IOException
    {
        socket.close();
    }
}
