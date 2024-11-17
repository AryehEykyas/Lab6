package lab6.main.exceptions;

/**
 * Exception is thrown when a non-existent command is called.
 */
public class NonExistentCommandException extends Exception
{
    @Override
    public String toString()
    {
        return "Несуществующая команда!";
    }
}