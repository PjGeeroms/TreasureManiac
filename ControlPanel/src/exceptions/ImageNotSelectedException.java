package exceptions;

import domein.*;

/**
 *
 * @author Robin
 */
public class ImageNotSelectedException extends IllegalArgumentException {

    /**
     *
     */
    public ImageNotSelectedException() {
        super("Choose an image!");
    }

    /**
     *
     * @param message
     */
    public ImageNotSelectedException(String message) {
        super(message);
    }

}
