package lab6.server;

/**
 * Class that is used to configure a server (host, port)
 * Contains file with start condition, file name and history of states.
 */
public class Configuration
{
    private static String host;
    private static int port;
    private static String startFileName;
    private static String historyFileName;

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

    public static String getStartFileName()
    {
        return startFileName;
    }

    public static void setStartFileName(String startFileName)
    {
        Configuration.startFileName = startFileName;
    }

    public static String getHistoryFileName()
    {
        return historyFileName;
    }

    public static void setHistoryFileName(String historyFileName)
    {
        Configuration.historyFileName = historyFileName;
    }
}