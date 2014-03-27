package exceptions;

import domein.*;

/**
 *
 * @author Simon
 */
public class TreasureIsUnconnectedException extends IllegalArgumentException {

    /**
     *
     */
    public TreasureIsUnconnectedException() {
        super("Treasure ID is not connected to a monster ID!");
    }

    /**
     *
     * @param message
     */
    public TreasureIsUnconnectedException(String message) {
        super(message);
    }

}
