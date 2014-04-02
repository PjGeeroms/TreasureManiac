package exceptions;

/**
 *
 * @author Robin
 */
public class EmptyArgumentException extends IllegalArgumentException {

    /**
     *
     */
    public EmptyArgumentException() {
        super("All mandatory fields have to be filled in!");
    }

    /**
     *
     * @param message
     */
    public EmptyArgumentException(String message) {
        super(message);
    }

}
