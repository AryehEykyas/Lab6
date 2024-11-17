package lab6.main.exceptions;

/**
 * Exception is thrown if there is no such an id in the collection.
 */
public class IncorrectIdInputException extends Exception
{
    @Override
    public String toString()
    {
        return "Такого id не существует";
    }
}