package lab6.main.consoles;

import java.util.Scanner;

/**
 * Class that is used for working with standard input.
 */
public class BaseConsole implements Console
{
    private final Scanner sc = new Scanner(System.in);

    public boolean hasNext()
    {
        return true;
    }

    public String getNextStr()
    {
        return sc.nextLine().strip();
    }

    public void print(String text)
    {
        System.out.println(text);
    }
}
