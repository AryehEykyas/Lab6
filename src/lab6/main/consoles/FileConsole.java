package lab6.main.consoles;

import lab6.main.interactors.FileInteractor;

/**
 * Class that is used for working with file I/O.
 */
public class FileConsole implements Console
{
    private final String[] lines;
    private int counter = 0;

    public FileConsole(String fileName)
    {
        String text = FileInteractor.readTextFromFile(fileName);
        lines = text.split("\n");
    }

    public boolean hasNext()
    {
        return counter < lines.length;
    }

    public String getNextStr()
    {
        return lines[counter++];
    }

    public void print(String text)
    {
        System.out.println(text);
    }
}