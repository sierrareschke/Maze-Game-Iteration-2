package csci.ooad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Maze {

    /* *
     *  FIELDS
     * */
    private static final Logger logger = LoggerFactory.getLogger(Character.class);

    private int numberOfRooms = 0;
    private Room[][] grid = null;
    private boolean isSquareMatrix;

    /* *
     *  CONSTRUCTORS
     * */


    // TODO - SHOULD THERE BE AN ERROR THROWN IN CONSTRUCTOR IF NOT SQUARE MATRIX

    Maze(List<Room> listOfRooms, List<Adventurer> adventurers, List<Creature> creatures, List<Food> foods) {
        int numRooms = listOfRooms.size();

        if(numRooms <= 0) {
            throw new IllegalArgumentException("Number of rooms must be greater than 0.");
        } else if (canFormSquareMatrix(numRooms)) {
            this.numberOfRooms = numRooms;
            this.grid = initializeRooms(listOfRooms);
            // TODO - RANDOMLY DISTRIBUTE ADVENTURERS, CREATURES, FOOD
        } else{
            throw new IllegalArgumentException("The number of rooms must be a square.");
        }
    }


    /* COMPLEX METHODS */




    // TODO -  bool areCreaturesAlive (Nolan) - checking for winner
    // TODO - areAdventurersAlive (Nolan)

    // TODO - getCreatures (Nolan) return a list of creatures
    public int getNumCreatures() {
        return 0; // TODO - placeholder, needs to be implemented
    }

    // TODO - getAdventurers (Nolan) return a list of adventurers
    public int getNumAdventurers() {
        return 0; // TODO - placeholder, needs to be implemented
    }

    // TODO - getFood (Nolan)
    public int getNumFoods() {
        return 0;
    } // for testing


    // TODO - RANDOMLY DISTRIBUTE CHARACTERS AND FOOD (Nolan)

    // TODO - removeCharacter(characterToDie) (Nolan)

    /**
     * Takes list of rooms and initializes them into a 2D grid
     * @param listOfRooms - an array of already instantiated Rooms
     * @return - a 2D array of Rooms
     */

    private Room[][] initializeRooms(List<Room> listOfRooms){
        if (canFormSquareMatrix(numberOfRooms)) {
            // Find the square root to determine matrix size
            int mazeDimensions = (int) Math.sqrt(this.numberOfRooms);
            // initialize the 2D array
            Room[][] matrix = new Room[mazeDimensions][mazeDimensions];
            for (int i = 0; i < listOfRooms.size(); i++) {
                // writes the rooms to the grid in order
                matrix[i/mazeDimensions][i%mazeDimensions] = listOfRooms.get(i);
            }
            this.grid = matrix;
            return matrix;
        } else {
            logger.error("Number of rooms must be a square matrix.");
            return null;
        }

    }

    // Helper methods
    private static boolean canFormSquareMatrix(int value) {
        // Calculate the square root of the value
        double sqrt = Math.sqrt(value);

        // Check if the square root is an integer (i.e., sqrt == floor(sqrt))
        return sqrt == Math.floor(sqrt);
    }


    // TODO - OVERRIDE TO STRING FOR PRINT MAZE
    /**
     * Northwest:
     *      Adventurers:
     * 		Creatures:
     * 		Food:
     * Northeast:
     *      Adventurers: Adventurer Sheri(health: 6.0)
     * 		Creatures: Creature Balrog(health: 3.0)
     * 		Food:
     * Southwest:
     *      Adventurers:
     * 		Creatures:
     * 		Food:
     * Southeast:
     *      Adventurers:
     * 		Creatures:
     * 		Food: Steak
     */

    // Getters
    public int getNumberOfRooms() {
        return numberOfRooms;
    }
    public boolean isSquareMatrix() {
        return this.isSquareMatrix;
    }
    public Room[][] getGrid() {
        return grid;
    }
    public Room getRoomInGrid(int x, int y){
        return grid[x][y];
    }

    // TODO - TEST FOR ACCURACY (Nolan)
    public ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        int mazeDimensions = (int) Math.sqrt(this.numberOfRooms);

        for (int i = 0; i < mazeDimensions; i++) {
            rooms.addAll(Arrays.asList(grid[i]).subList(0, mazeDimensions));
        }
        return rooms;
    }


    public boolean containsCharacter(Character character) {
        boolean found = false;
        ArrayList<Room> rooms = getRooms();
        for(Room room : rooms){
            if(room.hasCharacter(character)){
                found = true;
                break;
            }
        }
        return found;
    }

}
