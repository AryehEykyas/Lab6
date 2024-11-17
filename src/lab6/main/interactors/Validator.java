package lab6.main.interactors;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that is used to check if the input string is of numerical type, a command, e.t.c.
 */
public class Validator
{
    /**
     * Checks if the input string is an int value.
     *
     * @param value an input string.
     * @return true - if yes, false - otherwise.
     */
    public static boolean isInteger(String value)
    {
        try
        {
            Integer v = Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        return true;
    }

    /**
     * Checks if the input string is a double value.
     *
     * @param value an input string.
     * @return true - if yes, false - otherwise.
     */
    public static boolean isDouble(String value)
    {
        try
        {
            Double v = Double.parseDouble(value);
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        return true;
    }

    /**
     * Checks if the input string is a float value.
     *
     * @param value an input string.
     * @return true - if yes, false - otherwise.
     */
    public static boolean isFloat(String value)
    {
        try
        {
            Float v = Float.parseFloat(value);
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        return true;
    }

    /**
     * Checks if the input string is a long value.
     *
     * @param value an input string.
     * @return true - if yes, false - otherwise.
     */
    public static boolean isLong(String value)
    {
        try
        {
            Long v = Long.parseLong(value);
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        return true;
    }

    /**
     * Checks if the file with given fileName exists.
     *
     * @param fileName is a String that contains the name of file.
     * @return true - if exists, false - otherwise.
     */
    public static boolean isFile(String fileName)
    {
        return Files.exists(Path.of(fileName));
    }
}