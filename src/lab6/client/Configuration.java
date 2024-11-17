package lab6.client;

/**
 * Class that is used for client configuration (host, port)
 */
public class Configuration
{
    private static String host;
    private static int port;

    public static String getHost()
    {
        return host;
    }

    public static void setHost(String host)
    {
        Configuration.host = host;
    }

    public static int getPort()
    {
        return port;
    }

    public static void setPort(int port)
    {
        Configuration.port = port;
    }
}