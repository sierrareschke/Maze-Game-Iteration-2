package csci.ooad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    private static final Logger logger = LoggerFactory.getLogger(Character.class);
    List<Room> listOfRooms = new ArrayList<>();
    ArrayList<Adventurer> adventurers = new ArrayList<>();
    ArrayList<Creature> creatures = new ArrayList<>();
    ArrayList<Food> foodItems = new ArrayList<>();


    // create adventurer, creature, and food arrays to allow for creation of maze
    @BeforeEach
    void setUp() {
        // Create 2 adventurers
        Adventurer adventurerOne = new Adventurer("Bill");
        Adventurer adventurerTwo = new Adventurer("Ted");

        adventurers.add(adventurerOne);
        adventurers.add(adventurerTwo);

        // Create 5 creatures
        creatures.add(new Creature("Goblin"));
        creatures.add(new Creature("Orc"));
        creatures.add(new Creature("Troll"));
        creatures.add(new Creature("Dragon"));
        creatures.add(new Creature("Imp"));

        // Create 10 food items
        foodItems.add(new Food("Apple"));
        foodItems.add(new Food("Bread"));
        foodItems.add(new Food("Meat"));
        foodItems.add(new Food("Cheese"));
        foodItems.add(new Food("Berry"));
        foodItems.add(new Food("Fish"));
        foodItems.add(new Food("Chicken"));
        foodItems.add(new Food("Porridge"));
        foodItems.add(new Food("Honey"));
        foodItems.add(new Food("Soup"));
    }

    @Test
    public void testNewMazeCreation() {
        String[] roomNames = {"Room NW", "Room N", "Room NE", "Room W", "Room C", "Room E", "Room SW", "Room S", "Room SE"};

        int roomIndex = 0;
        for (String name : roomNames) {
            listOfRooms.add(new Room(name));
            roomIndex++;
        }

        Maze maze = new Maze(listOfRooms, adventurers, creatures, foodItems);

        assertNotEquals(0, maze.getNumberOfRooms());
        assertEquals(3, maze.getGrid().length, "Matrix should have 3 rows");

        //TODO: Remove this later...leaving it in for testing
        logger.info(Arrays.deepToString(maze.getGrid()));
    }

    // TODO
    @Test
    public void testInitializeRooms() {

        // Create the maze grid
        Maze maze = new Maze(listOfRooms, adventurers, creatures, foodItems);


    }
}
