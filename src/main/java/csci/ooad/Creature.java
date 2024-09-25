package csci.ooad;


public class Creature extends Character {
    public static final double CREATURE_INITIAL_HEALTH = 3.0;

    /* *
     *  CONSTRUCTORS
     * */
    public Creature(String creatureType) {
        super(creatureType, CREATURE_INITIAL_HEALTH);
    }

}
