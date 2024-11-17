package lab6.main.interactors;

import java.io.*;

/**
 * Class for working with files. Writing in and retrieving data from them.
 */
public class FileInteractor
{
    /**
     * Retrieves data from a file.
     *
     * @param fileName the file's name
     * @return String data(text) that was received.
     */
    public static String readTextFromFile(String fileName)
    {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName)))
        {
            String data = "";

            int bt = isr.read();
            while (bt != -1)
            {
                data += String.valueOf((char) bt);
                bt = isr.read();
            }

            return data;
        }
        catch (IOException e)
        {
            return "Ошибка ввода-вывода";
        }
    }

    public static void writeTextInFile(String fileName, String text)
    {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName)))
        {
            char[] elements = text.toCharArray();
            for (char el : elements)
            {
                bos.write(el);
            }

            bos.flush();
        }
        catch (IOException e)
        {
            System.out.println("Ошибка ввода-вывода");
        }
    }
}