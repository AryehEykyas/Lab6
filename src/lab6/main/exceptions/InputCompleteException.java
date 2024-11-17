package lab6.main.exceptions;

/**
 * Exception is thrown when the input is already completed but some new information is still missed.
 */
public class InputCompleteException extends Exception
{
    @Override
    public String toString()
    {
        return "Неполная информация";
    }
}