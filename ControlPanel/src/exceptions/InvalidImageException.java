package exceptions;

import domein.*;

/**
 *
 * @author Robin
 */
public class InvalidImageException extends IllegalArgumentException {

    /**
     *
     */
    public InvalidImageException() {
        super("Invalid image type, only jpg, gif, png are allowed");
    }

    /**
     *
     * @param message
     */
    public InvalidImageException(String message) {
        super(message);
    }

}
