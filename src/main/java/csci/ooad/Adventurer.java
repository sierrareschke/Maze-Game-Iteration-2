package csci.ooad;


public class Adventurer extends Character {
    public static final double ADVENTURER_INITIAL_HEALTH = 5.0;


    /* *
     *  CONSTRUCTORS
     * */

    public Adventurer(String name) {
        super(name, ADVENTURER_INITIAL_HEALTH);
    }

    /* *
     *  METHODS
     * */

    /**
     *  Increments the Adventurer's health by what's gained from food item
     * @param food - food item being eaten by the Adventurer
     */
    public void eatFood(Food food) {
        double healthGained = food.getHealthGranted();
        this.addToHealth(healthGained);
    }

}
