//Iteratie 1
package domein;

import exceptions.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robin
 */
public class Monster {

    private int id;
    private int power;
    private int defense;
    private int speed;
    private int awareness;
    private String name;
    private String avatar;
    private List<Treasure> treasures;               //NIET voor iteratie 1 (en waarschijnlijk onnodig)

    private final static int MAX = 15;
    private final static int MIN = 0;
    private final static int maxTreasures = 3;
    private final static int MAX_NAME = 25;
    private final static int MAX_DESCRIPTION = 50;
    private final static int MAX_AVATAR = 25;

    /**
     * Default constructor to create a monster with default values
     */
    public Monster() {
        this(0, "MONSTER", 0, 0, 0, 0, "default.png");
    }

    /**
     * Constructor to create a monster with custom values
     *
     * @param id the id of the monster in the database
     * @param name the name of the monster
     * @param avatar the path of the monster's avatar
     * @param power the power of the monster
     * @param defense the defense of the monster
     * @param speed the speed of the monster
     * @param awareness the awareness of the monster
     */
    public Monster(int id, String name, int power, int defense, int speed, int awareness, String avatar) {
        setId(id);
        setName(name);
        setAvatar(avatar);
        setPower(power);
        setDefense(defense);
        setSpeed(speed);
        setAwareness(awareness);
        treasures = new ArrayList<>();
    }

    /**
     * Link a treasure to the monster monster can only defend a maximum 3
     * treasures @link isFull()
     *
     * @param treasure the treasure that should be linked with the monster
     * @return true or false depending if the monster can be linked with a
     * treasure
     */
    public boolean addTreasure(Treasure treasure) {        //NIET voor iteratie 1 (en waarschijnlijk onnodig)     
        if (!isFull()) {
            treasures.add(treasure);
            return true;
        }
        return false;
    }

    /**
     * checks if monster can defend more treasure
     *
     * @return true or false depending if the monster can defend more treasure
     * or not
     */
    public boolean isFull() {                               //NIET voor iteratie 1 (en waarschijnlijk onnodig)
        return treasures.size() <= maxTreasures;             //Maximaal 3 schatten in 1 kamer, bij 1 monster
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get power of the monster
     *
     * @return the power of the monster
     */
    public int getPower() {
        return power;
    }

    /**
     * set the power of the monster
     *
     * @param power value to change power
     */
    public void setPower(int power) {
        if (power < MIN || power > MAX) {
            throw new OutOfRangeException();
        } else {
            this.power = power;                   //Eigenschappen moeten tussen 1 en 100 liggen
        }
    }

    /**
     * get defense of the monster
     *
     * @return the defense of the monster
     */
    public int getDefense() {
        return defense;
    }

    /**
     * set the defense of the monster
     *
     * @param defense value to change defense
     */
    public void setDefense(int defense) {
        if (defense < MIN || defense > MAX) {
            throw new OutOfRangeException();
        } else {
            this.defense = defense;
        }
    }

    /**
     * get speed of the monster
     *
     * @return the speed of the monster
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * set the speed of the monster
     *
     * @param speed value to change speed
     */
    public void setSpeed(int speed) {
        if (speed < MIN || speed > MAX) {
            throw new OutOfRangeException();
        }
        this.speed = speed;
    }

    /**
     * get awareness of the monster
     *
     * @return return the awareness of the monster
     */
    public int getAwareness() {
        return awareness;
    }

    /**
     * set the awareness of the monster
     *
     * @param awareness value to change the awareness
     */
    public void setAwareness(int awareness) {
        if (awareness < MIN || awareness > MAX) {
            throw new OutOfRangeException();
        } else {
            this.awareness = awareness;
        }
    }

    /**
     * get the name of the monster
     *
     * @return the name of the monster
     */
    public String getName() {
        return name;
    }

    /**
     * set the monster's name
     *
     * @param name the name of the monster
     */
    public void setName(String name) {
        if (name.equals("")) {
            throw new EmptyArgumentException();
        }
        if (name.length() > MAX_NAME) {
            throw new OutOfRangeException();
        }
        this.name = name;
    }

    /**
     * get the path of the monster avatar
     *
     * @return path of the monster avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * set the path of the monster avatar
     *
     * @param avatar path of the monster avatar
     */
    public void setAvatar(String avatar) {
        /*if (avatar.equals("") || avatar.equals(".jpg") || avatar.equals(".png")) {
         throw new EmptyArgumentException();
         }
         if (avatar.length() > MAX_AVATAR) {
         throw new OutOfRangeException();
         }
         if ((avatar.indexOf(".png") == -1 || avatar.indexOf(".png") != avatar.length() - 4)
         && (avatar.indexOf(".jpg") == -1 || avatar.indexOf(".jpg") != avatar.length() - 4)
         && (avatar.indexOf(".gif") == -1 || avatar.indexOf(".gif") != avatar.length() - 4)) {
         throw new InvalidImageException();
         }
         if (Main.class.getResourceAsStream("/images/monsters/" + avatar) == null) {
         throw new ImageNotSelectedException();
         }*/
        if (avatar == null) {
            throw new ImageNotSelectedException();
        }

        this.avatar = avatar;

    }

    /*Niet voor iteratie 1
     public int calcTotalAwareness() {
     int total = awareness + (int) (Math.random() * 6) + 1;
     return total;
     }
     public int calcTotalPower() {
     int total = power + (int) (Math.random() * 6) + 1;
     return total;
     }
     public int calcTotalDefense() {
     int total = defense + (int) (Math.random() * 6) + 1;
     return total;
     }
     public int calcTotalSpeed() {
     int total = speed + (int) (Math.random() * 6) + 1;
     return total;
     }
     */
    /**
     *
     * @return
     */
    public static int getMAX() {
        return MAX;
    }

    /**
     *
     * @return
     */
    public static int getMIN() {
        return MIN;
    }

    /**
     *
     * @return
     */
    public static int getMAX_NAME() {
        return MAX_NAME;
    }

    /**
     *
     * @return
     */
    public static int getMAX_DESCRIPTION() {
        return MAX_DESCRIPTION;
    }

    /**
     *
     * @return
     */
    public static int getMAX_AVATAR() {
        return MAX_AVATAR;
    }
    
    @Override
    public String toString() {
        String text;
        if(id >= 10)text = "[#" + id + "]\t" + name;
        else{text = "[#" + id + "]\t\t" + name;}
        return text;
        
    }

}
