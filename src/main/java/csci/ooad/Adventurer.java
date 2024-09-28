package csci.ooad;


import java.util.HashMap;

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
     * Increments the Adventurer's health by what's gained from food item
     *
     * @param food - food item being eaten by the Adventurer
     * @param maze
     */
    public void eatFood(Food food, Maze maze) {
        double healthGained = food.getHealthGranted();
        HashMap<String, Integer> adventurerRoom = super.currentRoomCoordinates(maze);

        this.addToHealth(healthGained);
        // TODO - REMOVE FOOD (currently a constant amount of food??)
    }

}
