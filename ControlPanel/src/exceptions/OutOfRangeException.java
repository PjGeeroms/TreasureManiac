package exceptions;

/**
 *
 * @author Robin
 */
public class OutOfRangeException extends IllegalArgumentException {

    /**
     * Default constructor for OutOfRangeException
     */
    public OutOfRangeException() {
        super("Out of range: Respect the boundaries!");
    }

    /**
     * Constructor for OutOfRangeException in which you can choose the
     * exception's message
     *
     * @param message information given to the user when exception is thrown
     */
    public OutOfRangeException(String message) {
        super(message);
    }

}
