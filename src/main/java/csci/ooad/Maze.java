package csci.ooad;

import java.util.*;

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
            generateGameState(adventurers, creatures, foods);
        } else{
            throw new IllegalArgumentException("The number of rooms must be a square.");
        }
    }


    /* COMPLEX METHODS */


    public boolean areCreaturesAlive(){
        ArrayList<Creature> allAlive = new ArrayList<>();
        List<Room> allRooms = this.getRooms();
        for(Room room: allRooms){
            List<Creature> occupants = room.getCreatures();
            for(Creature creature: occupants){
                if(creature.isAlive()){
                    allAlive.add(creature);
                }
            }
        }
        return !allAlive.isEmpty();
    }

    public boolean areAdventuresAlive(){
        ArrayList<Adventurer> allAlive = new ArrayList<>();
        List<Room> allRooms = this.getRooms();
        for(Room room: allRooms){
            List<Adventurer> occupants = room.getAdventurers();
            for(Adventurer creature: occupants){
                if(creature.isAlive()){
                    allAlive.add(creature);
                }
            }
        }
        return !allAlive.isEmpty();
    }

    public ArrayList<Creature> getAllCreatures(){
        ArrayList<Creature> allCreatures = new ArrayList<>();
        List<Room> allRooms = this.getRooms();
        for(Room room: allRooms){
            List<Creature> occupants = room.getCreatures();
            allCreatures.addAll(occupants);
        }
        return allCreatures;
    }

    public ArrayList<Adventurer> getAllAdventurers(){
        ArrayList<Adventurer> allAdventurers = new ArrayList<>();
        List<Room> allRooms = this.getRooms();
        for(Room room: allRooms){
            List<Adventurer> occupants = room.getAdventurers();
            allAdventurers.addAll(occupants);
        }
        return allAdventurers;
    }

    public ArrayList<Food> getAllFoods(){
        ArrayList<Food> allFoods = new ArrayList<>();
        List<Room> allRooms = this.getRooms();
        for(Room room: allRooms){
            List<Food> food = room.getFood();
            allFoods.addAll(food);
        }
        return allFoods;
    }

    public int getNumAdventurers() {
        return getAllAdventurers().size();
    }

    public int getNumCreatures() {
        return getAllCreatures().size();
    }

    public int getNumFoods() {
        return this.getAllFoods().size();
    }

    private void generateGameState(List<Adventurer> adventurers, List<Creature> creatures, List<Food> foods) {
        for(Adventurer adventurer : adventurers) {
            adventurer.spawn(this);
        }
        for(Character character : creatures) {
            character.spawn(this);
        }
        for(Food food : foods) {
            food.distribute(this);
        }
    }

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

    public Room[][] getGrid() {
        return grid;
    }
    public Room getRoomInGrid(int x, int y){
        return grid[x][y];
    }

    public ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        grid = this.getGrid();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Room currentRoom = grid[i][j];
                rooms.add(currentRoom);
            }
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

    public void purge(Character character) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Room currentRoom = grid[i][j];
                currentRoom.removeCharacter(character);
            }
        }
    }
}
