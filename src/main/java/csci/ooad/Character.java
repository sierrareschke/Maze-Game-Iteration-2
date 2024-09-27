package csci.ooad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Random;

public abstract class Character {

    /* *
     *  FIELDS
     * */

    private static final Logger logger = LoggerFactory.getLogger(Character.class);

    private String name;
    protected double health;


    /* *
    *  CONSTRUCTORS
    * */

    Character(String name, double health) {
        this.name = name;
        this.health = health; // initial health set to 5.0 for adventurers and 3.0 for creatures
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

    public void move(Maze maze) {
        Random rand = new Random();
        HashMap<String, Integer> coords = this.currentRoomCoordinates(maze);

        // Grid coordinates of the current room
        int x = coords.get("x");
        int y = coords.get("y");
        Room currentRoom = maze.getRoomInGrid(x, y);

        // Take one random step in either direction (up/down or left/right)
        int moveX = (int) (Math.random() * 3) - 1;
        int moveY = (int) (Math.random() * 3) - 1;

        // Adjust the coordinates to move to a neighboring room
        int newX = x;
        int newY = y;
        while (newX != x && newY != y) {
            boolean moveAlongX = rand.nextBoolean();
            if (moveAlongX) {
                newX = Math.max(0, Math.min(2, x + moveX));
                newY = y;
            } else {
                newX = x;
                newY = Math.max(0, Math.min(2, y + moveY));
            }
        }

        Room newRoom = maze.getRoomInGrid(newX, newY);

        // Move the character to the new room
        currentRoom.removeCharacter(this);
        newRoom.addOccupant(this);

        // Each move decrements health by 0.25
        this.health -= 0.25;

    }

    public void spawn(Maze maze){
        Random random = new Random();
        int mazeDimensions = (int) Math.sqrt(maze.getNumberOfRooms());

        // Generate a random number between 0 and 2 (inclusive)
        int randomX = random.nextInt(mazeDimensions);
        int randomY = random.nextInt(mazeDimensions);
        System.out.println(randomX + " " + randomY);

        Room room  = maze.getRoomInGrid(randomX,randomY);
        room.addOccupant(this);
        System.out.println(room);
    }

    public HashMap<String, Integer> currentRoomCoordinates(Maze maze) {
        Room[][] grid = maze.getGrid();
        // Use 'this' to search for the current character instance in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Room currentRoom = grid[i][j];
                if (currentRoom.hasCharacter(this)) {
                    // Create a HashMap with the coordinates {x: i, y: j}
                    HashMap<String, Integer> coordinates = new HashMap<>();
                    coordinates.put("x", i);
                    coordinates.put("y", j);
                    return coordinates;
                }
            }
        }
        return null;

    }

    public boolean isAlive(){
        return this.health > 0;
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
