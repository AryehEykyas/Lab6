package lab6.main.consoles;

/**
 * Interface that is used for consoles.
 * Consoles are used for I/O processing, and for retrieving and writing data in files.
 */
public interface Console
{
    /**
     * Checks is it possible to receive the next string.
     *
     * @return boolean true or false.
     */
    public boolean hasNext();

    /**
     * Gets the next string.
     *
     * @return String that is read the next.
     */
    public String getNextStr();

    /**
     * Writes data in.
     *
     * @param data tha is written in.
     */
    public void print(String data);
}