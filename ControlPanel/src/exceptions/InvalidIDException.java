package exceptions;

import domein.*;

/**
 *
 * @author Robin
 */
public class InvalidIDException extends IllegalArgumentException {

    /**
     *
     */
    public InvalidIDException() {
        super("ID isn't present in the database!");
    }

    /**
     *
     * @param message
     */
    public InvalidIDException(String message) {
        super(message);
    }

}
