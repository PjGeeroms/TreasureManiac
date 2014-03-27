package exceptions;

import domein.*;

/**
 *
 * @author Robin
 */
public class OutOfRangeException extends IllegalArgumentException {

    /**
     *
     */
    public OutOfRangeException() {
        super("Out of range: Respect the boundaries!");
    }

    /**
     *
     * @param message
     */
    public OutOfRangeException(String message) {
        super(message);
    }

}
