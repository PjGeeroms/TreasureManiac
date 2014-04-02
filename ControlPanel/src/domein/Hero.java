//NIET voor iteratie 2
package domein;

/**
 *
 * @author Robin
 */
public class Hero {

    private int power;
    private int defense;
    private int speed;
    private int awareness;
    private String name;
    private String avatar;
    private Inventory inventory;

    private final static int MAX = 8;                 //Minimum en maximum-waarden voor de eigenschappen
    private final static int MIN = 0;

    /**
     * Create a Hero object with default values
     */
    public Hero() {
        this("", "", 0, 0, 0, 0);
    }

    /**
     * Create a Hero object with custom values
     *
     * @param name Name of your hero
     * @param avatar The avatar of your hero
     * @param power power of your hero
     * @param defense defense of your hero
     * @param speed speed of your hero
     * @param awareness awareness of your hero
     */
    public Hero(String name, String avatar, int power, int defense, int speed, int awareness) {
        setName(name);
        setAvatar(avatar);
        setPower(power);
        setDefense(defense);
        setSpeed(speed);
        setAwareness(awareness);
        inventory = new Inventory();
    }                                               //Bij het spel zelf ervoor zorgen dat alle nodige waarden zijn ingegeven, meerdere constructors zijn niet nodig

    /**
     *
     * @return the power of the hero
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets the hero's power
     *
     * @param power new value for power
     */
    public void setPower(int power) {
        this.power = (power >= MIN && power <= MAX) ? power : MIN;     //Eigenschappen liggen tussen 0 en 8
    }

    /**
     *
     * @return the defense of the hero
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Sets the hero's defense
     *
     * @param defense new value for defense
     */
    public void setDefense(int defense) {
        this.defense = (defense >= MIN && defense <= MAX) ? defense : MIN;
    }

    /**
     *
     * @return the speed of the hero
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the hero's speed
     *
     * @param speed new value for speed
     */
    public void setSpeed(int speed) {
        this.speed = (speed >= MIN && speed <= MAX) ? speed : MIN;
    }

    /**
     *
     * @return the awareness of the hero
     */
    public double getAwareness() {
        return awareness;
    }

    /**
     * Sets the hero's awareness
     *
     * @param awareness new value for awareness
     */
    public void setAwareness(int awareness) {
        this.awareness = (awareness >= MIN && awareness <= MAX) ? awareness : MIN;
    }

    /**
     * Give the name of the hero
     *
     * @return the hero's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the hero
     *
     * @param name changes the hero's name
     */
    public void setName(String name) {
        this.name = (!name.equals("")) ? name : "Treasure Maniac";          //Naam is per default Treasure Maniac indien niets ingegeven
    }

    /**
     * will return the path of the avatar
     *
     * @return returns the path of the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * set the hero's avatar path
     *
     * @param avatar changes the path of the avatar, resulting in changing the
     * avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = (!avatar.equals("")) ? avatar : "";           //Ofwel een default-avatar ofwel geen
    }

    /**
     *
     * @return total awareness of the hero
     */
    public int calcTotalAwareness() {

        int total = awareness + inventory.getInvAwareness();
        if (total >= MIN) {
            total += (int) (Math.random() * 6) + 1;
        } else {
            total = (int) (Math.random() * 6) + 1;         //Totaal=minimum van het vaste totaal (zonder randomwaarde) is 0
        }
        return total;
    }

    /**
     *
     * @return total power of the hero
     */
    public int calcTotalPower() {
        int total = power + inventory.getInvPower();
        if (total >= MIN) {
            total += (int) (Math.random() * 6) + 1;
        } else {
            total = (int) (Math.random() * 6) + 1;
        }
        return total;
    }

    /**
     *
     * @return total defense of the hero
     */
    public int calcTotalDefense() {
        int total = defense + inventory.getInvDefense();
        if (total >= MIN) {
            total += (int) (Math.random() * 6) + 1;
        } else {
            total = (int) (Math.random() * 6) + 1;         //Totaal=minimum van het vaste totaal (zonder randomwaarde) is 0
        }
        return total;
    }

    /**
     *
     * @return total speed of the hero
     */
    public int calcTotalSpeed() {
        int total = speed + inventory.getInvSpeed();
        if (total >= MIN) {
            total += (int) (Math.random() * 6) + 1;
        } else {
            total = (int) (Math.random() * 6) + 1;         //Totaal=minimum van het vaste totaal (zonder randomwaarde) is 0
        }
        return total;
    }
}
