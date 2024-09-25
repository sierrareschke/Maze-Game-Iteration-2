package csci.ooad;


public class Adventurer extends Character {

    /* *
     *  CONSTRUCTORS
     * */

    public Adventurer(String name) {
        super(name);
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
