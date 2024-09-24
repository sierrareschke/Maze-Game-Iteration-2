package csci.ooad;


import java.util.HashMap;
import java.util.Random;

public class Character {

    private String name;
    private double health;

    /**
     * Constructor for Character objects. Sets name and initial health (5.0)
     * @param name - name of Character
     */
    Character(String name) {
        this.name = name;
        this.health = 5.0; // initial health set to 5.0
    }

    /**
     * returns the name of the Character
     * @return - name
     */
    public String getName() {return name;}

    /**
     * Method to get current health
     * @return health - the Character's current health
     */
    public double getHealth() {
        return this.health;
    }

    // Setters
    public void setHealth(double health){
        this.health = health;
    }

    /**
     * Decrements the Character's health by adding a negative number
     * @param numToSubtract - a negative value passed to decrease the character's health by
     */
    public void subtractFromHealth(double numToSubtract) {
        // check that numToSubtract is negative
        if (numToSubtract < 0) {
            this.health += numToSubtract; // adding negative number subtracts health
        } else {
            System.out.println("Value must be negative to subtract from health.");
        }
    }

    public void move(Maze maze, HashMap<String, Integer> coords) {
        Random rand = new Random();

        // Grid coordinates of the current room
        int x = coords.get("x");
        int y = coords.get("y");
        Room currentRoom = maze.getRoomInGrid(x, y);

        // Take one random step in either direction
        // TODO: need to change so that it only moves one step at a time
        int moveX = (int) (Math.random() * 3) - 1;
        int moveY = (int) (Math.random() * 3) - 1;

        // Adjust the coordinates to move to a neighboring room
        int newX = Math.max(0, Math.min(3, x + moveX));
        int newY = Math.max(0, Math.min(3, y + moveY));
        Room newRoom = maze.getRoomInGrid(newX, newY);

        // Move the character to the new room
        currentRoom.removeCharacter(this);
        newRoom.addOccupant(this);

    }

    public void spawn(Maze maze){
        Random random = new Random();
        Room[][] grid = maze.getGrid();

        // Generate a random number between 0 and 2 (inclusive)
        int randomX = random.nextInt(3);
        int randomY = random.nextInt(3);

        Room room  = maze.getRoomInGrid(randomX,randomY);
        room.addOccupant(this);
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
