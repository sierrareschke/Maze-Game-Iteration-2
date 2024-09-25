package csci.ooad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Character {

    /* *
     *  FIELDS
     * */

    private static final Logger logger = LoggerFactory.getLogger(Character.class);

    private String name;
    private double health;

    /* *
    *  CONSTRUCTORS
    * */

    Character(String name) {
        this.name = name;
        this.health = 5.0; // initial health set to 5.0
    }

    /* *
     *  METHODS
     * */

    /* GETTERS & SETTERS */

    public String getName() {return name;}

    public double getHealth() {
        return this.health;
    }



    /* COMPLEX METHODS */

    /**
     * Decrements the Character's health by value passed
     * @param numToSubtract - character's health substracts by number passed
     */
    public void subtractFromHealth(double numToSubtract) {
        if (numToSubtract > 0) {
            this.health -= numToSubtract;
        } else {
            logger.error("Value to subtract from health must be positive");
        }
    }

    /**
     * Increments the Character's health by value passed
     * @param numToAdd - character's health increased by number passed
     */
    public void addToHealth(double numToAdd) {
        if(numToAdd > 0) {
            this.health += numToAdd;
        } else {
            logger.error("Value to add to health must be positive");
        }
    }


    /**
     * toString method to return Character's name(health: health)
     * @return string of Character's name and health
     * Ex. testAdventurer(health: 5.0)
     */
    @Override
    public String toString() {
        return name + "(health: " + health + ")";
    }

}
