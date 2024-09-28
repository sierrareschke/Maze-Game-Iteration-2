package csci.ooad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Food {

    /* *
     *  FIELDS
     * */
    private static final Logger logger = LoggerFactory.getLogger(Food.class);
    private String name;
    private int healthGranted;

    /* *
     *  CONSTRUCTORS
     * */

    public Food() {
        // Generate random food named
        List<String> foodNameOptions = new ArrayList<>();
        foodNameOptions.add("Bread");
        foodNameOptions.add("Cake");
        foodNameOptions.add("Chicken");
        foodNameOptions.add("Fish");
        foodNameOptions.add("Steak");

        // Set food name
        Random random = new Random();
        int randomIndex = random.nextInt(foodNameOptions.size());
        this.name = foodNameOptions.get(randomIndex);

        // Set healthGranted to 1 (as defined by assignment
        this.healthGranted = 1;
    }

    public Food(String name) {
        this.name = name;
        this.healthGranted = 1;
    }

    /* *
     *  METHODS
     * */

    public String getName() {
        return this.name;
    }

    public int getHealthGranted() {
        return this.healthGranted;
    }

    public void distribute(Maze maze){

        Random random = new Random();
        int mazeDimensions = (int) Math.sqrt(maze.getNumberOfRooms());

        // Generate a random number between 0 and 2 (inclusive)
        int randomX = random.nextInt(mazeDimensions);
        int randomY = random.nextInt(mazeDimensions);
//        logger.info(randomX + " " + randomY);

        Room room  = maze.getRoomInGrid(randomX,randomY);
        room.addFood(this);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
