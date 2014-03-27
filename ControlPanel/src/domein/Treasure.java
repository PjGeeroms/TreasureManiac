//Iteratie 1
package domein;

import exceptions.*;
import gui.Main;
import java.util.List;

/**
 *
 * @author Robin
 */
public class Treasure {

    private int id;
    private int value;
    private String name;
    private int power;
    private int defense;
    private int speed;
    private int awareness;
    private String description;
    private String avatar;
    private List<Monster> monsters;

    private final static int MAX = 3;
    private final static int MIN = -3;
    private final static int MAX_NAME = 25;
    private final static int MAX_DESCRIPTION = 50;
    //private final static int MAX_AVATAR = 25;

    /**
     * Default constructor to create a treasure with default values
     */
    public Treasure() {
        this(0, "TREASURE", 0, "DESCRIPTION", 0, 0, 0, 0, "default.png");
    }

    /**
     * Constructor to create a treasure with custom values
     *
     * @param id
     * @param name name of the treasure
     * @param value
     * @param description description of the treasure
     * @param avatar path of the avatar
     * @param power power of the treasure
     * @param defense defense of the treasure
     * @param speed speed of the treasure
     * @param awareness awareness of the treasure
     */
    public Treasure(int id, String name, int value, String description, int power, int defense, int speed, int awareness, String avatar) {
        setId(id);
        setName(name);
        setDescription(description);
        setAvatar(avatar);
        setPower(power);
        setDefense(defense);
        setSpeed(speed);
        setAwareness(awareness);
        setValue(value);
    }

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
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    protected void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(int value) {
        if (value < 0) {
            throw new OutOfRangeException();
        } else {
            this.value = value;
        }
    }

    /**
     * get the power of the treasure
     *
     * @return the power of the treasure
     */
    public int getPower() {
        return power;
    }

    /**
     * set the power of the treasure
     *
     * @param power value to change the power of the treasure
     */
    public void setPower(int power) {
        if (power < MIN || power > MAX) {
            throw new OutOfRangeException();
        } else {
            this.power = power;
        }
    }

    /**
     * get the defense of the treasure
     *
     * @return the defense of the treasure
     */
    public int getDefense() {
        return defense;
    }

    /**
     * set the defense of the treasure
     *
     * @param defense value to change the defense of the treasure
     */
    public void setDefense(int defense) {
        if (defense < MIN || defense > MAX) {
            throw new OutOfRangeException();
        } else {
            this.defense = defense;
        }
    }

    /**
     * get the speed of the treasure
     *
     * @return the speed of the treasure
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * set the speed of the treasure
     *
     * @param speed value to change the speed of the treasure
     */
    public void setSpeed(int speed) {
        if (speed < MIN || speed > MAX) {
            throw new OutOfRangeException();
        } else {
            this.speed = speed;
        }
    }

    /**
     * get the awareness of the treasure
     *
     * @return the awareness of the treasure
     */
    public int getAwareness() {
        return awareness;
    }

    /**
     * set the awareness of the treasure
     *
     * @param awareness value to change the awareness of the treasure
     */
    public void setAwareness(int awareness) {
        if (awareness < MIN || awareness > MAX) {
            throw new OutOfRangeException();
        } else {
            this.awareness = awareness;
        }
    }

    /**
     * get the name of the treasure
     *
     * @return the name of the treasure
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the treasure
     *
     * @param name value to change the name of the treasure
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
     * get the path of the avatar
     *
     * @return path of the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * set the path of the avatar
     *
     * @param avatar path of the avatar
     */
    public void setAvatar(String avatar) {
        /*if (avatar.equals("") || avatar.equals(".jpg") || avatar.equals(".png") || avatar.equals(".gif")) {
         throw new EmptyArgumentException();
         }
         if (avatar.length() > MAX_AVATAR) {
         throw new OutOfRangeException();
         }*/
        //Eerst database leegmaken
        /*if ((avatar.indexOf(".png") == -1 || avatar.indexOf(".png") != avatar.length() - 4)
         && (avatar.indexOf(".jpg") == -1 || avatar.indexOf(".jpg") != avatar.length() - 4)
         && (avatar.indexOf(".gif") == -1 || avatar.indexOf(".gif") != avatar.length() - 4)) {
         throw new InvalidImageException();
         }
         if (Main.class.getResourceAsStream("/images/treasures/" + avatar) == null) {
         throw new ImageNotFoundException();
         }*/
        if (avatar == null) {
            throw new ImageNotSelectedException();
        }
        this.avatar = avatar;

    }

    /**
     * get the description of the treasure
     *
     * @return the description of the treasure
     */
    public String getDescription() {
        return description;
    }

    /**
     * set the description of the treasure
     *
     * @param description value to change the description of the treasure
     */
    public void setDescription(String description) {
        if (description.equals("")) {
            throw new EmptyArgumentException();
        }
        if (description.length() > MAX_DESCRIPTION) {
            throw new OutOfRangeException();
        }
        this.description = description;

    }

    @Override
    public String toString() {
        return "ID: " + id + "         Name: " + name;
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
    /* public static int getMAX_AVATAR() {
     return MAX_AVATAR;
     }*/
}
