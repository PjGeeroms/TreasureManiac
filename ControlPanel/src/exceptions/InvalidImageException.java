package exceptions;

import domein.*;

/**
 *
 * @author Robin
 */
public class InvalidImageException extends IllegalArgumentException {

    /**
     * Default constructor for InvalidImageException
     */
    public InvalidImageException() {
        super("Invalid image type, only jpg, gif, png are allowed");
    }

    /**
     * Constructor for InvalidImageException in which you can choose the
     * exception's message
     *
     * @param message information given to the user when exception is thrown
     */
    public InvalidImageException(String message) {
        super(message);
    }

}
