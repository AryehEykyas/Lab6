package lab6.main.consoles;

/**
 * Class that is used fow working with string console.
 * Output is gathered in special array.
 */
public class StringConsole implements Console
{
    private String str = "";

    @Override
    public boolean hasNext()
    {
        return false;
    }

    @Override
    public String getNextStr()
    {
        return null;
    }

    @Override
    public void print(String text)
    {
        str += text + "\n";
    }

    public String getText()
    {
        return str.strip();
    }
}
