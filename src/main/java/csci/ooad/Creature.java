package csci.ooad;


public class Creature extends Character {
    public static final double CREATURE_INITIAL_HEALTH = 3.0;

    /* *
     *  CONSTRUCTORS
     * */
    Creature(String name) {
        super(name, CREATURE_INITIAL_HEALTH);
    }

}
