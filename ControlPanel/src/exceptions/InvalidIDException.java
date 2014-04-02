package exceptions;

import domein.*;

/**
 *
 * @author Robin
 */
public class InvalidIDException extends IllegalArgumentException {

    /**
     * Default constructor for InvalidIDException
     */
    public InvalidIDException() {
        super("ID isn't present in the database!");
    }

    /**
     * Constructor for InvalidException in which you can choose the exception's
     * message
     *
     * @param message information given to the user when exception is thrown
     */
    public InvalidIDException(String message) {
        super(message);
    }

}
