//NIET voor iteratie 2 (en waarschijnlijk onnodig)
package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robin
 */
public class Inventory {

    private List<Treasure> treasures;

    private int invPower;
    private int invDefense;
    private int invSpeed;
    private int invAwareness;

    /**
     * Creates a new Inventory, containing all the treasures that were collected
     */
    public Inventory() {
        invPower = 0;
        invDefense = 0;
        invSpeed = 0;
        invAwareness = 0;
        treasures = new ArrayList<>();
    }

    /**
     * returns all the treasures collected
     *
     * @return a list containing all treasures that were collected
     */
    public List<Treasure> giveInventory() {
        return treasures;
    }

    /**
     * Checks if any treasures were collected
     *
     * @return true or false, true means nothing was collected, false means our
     * hero collected minimum one treasure
     */
    public boolean isEmpty() {
        return treasures.isEmpty();
    }

    /**
     * Add a treasure to the inventory
     *
     * @param treasure the treasure that should be added to the inventory
     */
    public void addTreasure(Treasure treasure) {

        invPower += treasure.getPower();
        invDefense += treasure.getDefense();
        invSpeed += treasure.getSpeed();
        invAwareness += treasure.getAwareness();
        treasures.add(treasure);
    }

    /**
     * Remove a treasure from the inventory
     *
     * @param treasure the treasure that should be deleted from the inventory
     */
    public void removeTreasure(Treasure treasure) {
        invPower -= treasure.getPower();
        invDefense -= treasure.getDefense();
        invSpeed -= treasure.getSpeed();
        invAwareness -= treasure.getAwareness();
        treasures.remove(treasure);
    }

    /**
     *
     * @return power
     */
    public int getInvPower() {
        return invPower;
    }

    /**
     *
     * @return defense
     */
    public int getInvDefense() {
        return invDefense;
    }

    /**
     *
     * @return speed
     */
    public int getInvSpeed() {
        return invSpeed;
    }

    /**
     *
     * @return awareness
     */
    public int getInvAwareness() {
        return invAwareness;
    }
}
