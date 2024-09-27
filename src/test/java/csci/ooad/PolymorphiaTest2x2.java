package csci.ooad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PolymorphiaTest2x2 {

    private static final Logger logger = LoggerFactory.getLogger(PolymorphiaTest2x2.class);

    Polymorphia polymorphia;
    Maze maze;
    ArrayList<Adventurer> adventurers;
    ArrayList<Creature> creatures;
    ArrayList<Food> foods;


    @BeforeEach
    void setUp() {

        adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("Bill"));
        adventurers.add(new Adventurer("Ted"));

        creatures = new ArrayList<>();
        creatures.add(new Creature("Ogre"));
        creatures.add(new Creature("Troll"));
        creatures.add(new Creature("Werewolf"));
        creatures.add(new Creature("Vampire"));
        creatures.add(new Creature("Zombie"));

        foods = new ArrayList<>();
        foods.add(new Food("Apple"));
        foods.add(new Food("Bread"));
        foods.add(new Food("Carrot"));
        foods.add(new Food("Pork"));
        foods.add(new Food("Steak"));
        foods.add(new Food("Chicken"));
        foods.add(new Food("Pumpkin Pie"));
        foods.add(new Food("Cake"));
        foods.add(new Food("Mango"));
        foods.add(new Food("Pear"));

        ArrayList<Room> myRooms = new ArrayList<>();
        Room roomOne = new Room("Room One");
        Room roomTwo = new Room("Room Two");
        Room roomThree = new Room("Room Three");
        Room roomFour = new Room("Room Four");

        myRooms.add(roomOne);
        myRooms.add(roomTwo);
        myRooms.add(roomThree);
        myRooms.add(roomFour);

        ArrayList<Adventurer> myAdventurers = new ArrayList<>();
        myAdventurers.add(adventurers.get(0));

        ArrayList<Creature> myCreatures = new ArrayList<>();
        myCreatures.add(creatures.get(0));

        maze = new Maze(myRooms,myAdventurers,myCreatures,foods);

        polymorphia = new Polymorphia(maze);

    }


    /* ----------- 2x2 TESTS -------------- */

    @Test
    void test2x2Init() {
        ArrayList<Room> myRooms = new ArrayList<>();
        Room roomOne = new Room("Room One");
        Room roomTwo = new Room("Room Two");
        Room roomThree = new Room("Room Three");
        Room roomFour = new Room("Room Four");

        myRooms.add(roomOne);
        myRooms.add(roomTwo);
        myRooms.add(roomThree);
        myRooms.add(roomFour);

        ArrayList<Adventurer> myAdventurers = new ArrayList<>();
        myAdventurers.add(adventurers.get(0));

        ArrayList<Creature> myCreatures = new ArrayList<>();
        myCreatures.add(creatures.get(0));

        maze = new Maze(myRooms,myAdventurers,myCreatures,foods);

        polymorphia = new Polymorphia(maze);

        // Assertions to check the initialization
        // Check that there are 4 rooms
        assertEquals(4, maze.getNumberOfRooms(), "The maze should have 4 rooms.");

        // Check that there is 1 adventurer
        assertEquals(1, maze.getNumAdventurers(), "There should be 1 adventurer in the maze.");

        // Check that there is 1 creature
        assertEquals(1, maze.getNumCreatures(), "There should be 1 creature in the maze.");

        // Check that there are 10 food items
        assertEquals(10, maze.getNumFoods(), "There should be 10 food items in the maze.");

        // Check that adventurers, creatures, and food are randomly distributed among the rooms
        boolean adventurersDistributed = false;
        boolean creaturesDistributed = false;
        boolean foodDistributed = false;

        for (Room room : maze.getRooms()) {
            // Check if adventurers are present in different rooms
            if (!room.getAdventurers().isEmpty()) {
                adventurersDistributed = true;
            }

            // Check if creatures are present in different rooms
            if (!room.getCreatures().isEmpty()) {
                creaturesDistributed = true;
            }

            // Check if food items are present in different rooms
            if (!room.getFood().isEmpty()) {
                foodDistributed = true;
            }
        }

        assertTrue(adventurersDistributed, "Adventurers should be distributed among the rooms.");
        assertTrue(creaturesDistributed, "Creatures should be distributed among the rooms.");
        assertTrue(foodDistributed, "Food items should be distributed among the rooms.");

    }

    @Test
    void test2x2TakeTurn() {

        ArrayList<Room> myRooms = new ArrayList<>();
        Room roomOne = new Room("Room One");
        Room roomTwo = new Room("Room Two");
        Room roomThree = new Room("Room Three");
        Room roomFour = new Room("Room Four");

        myRooms.add(roomOne);
        myRooms.add(roomTwo);
        myRooms.add(roomThree);
        myRooms.add(roomFour);

        ArrayList<Adventurer> myAdventurers = new ArrayList<>();
        myAdventurers.add(adventurers.get(0));

        ArrayList<Creature> myCreatures = new ArrayList<>();
        myCreatures.add(creatures.get(0));

        maze = new Maze(myRooms,myAdventurers,myCreatures,foods);

        polymorphia = new Polymorphia(maze);

        int totalTurns = 10; // We will simulate 10 turns
        int noAdventurersCount = 0;
        int fightsCount = 0;
        int adventurersNoFoodCount = 0;
        int adventurersEatFoodCount = 0;

        // Run takeTurn for a number of iterations
        for (int i = 0; i < totalTurns; i++) {
            polymorphia.takeTurn();

            // Iterate through all rooms to count occurrences of each scenario
            for (Room room : maze.getRooms()) {
                int adventurersPresent = room.getAdventurers().size();
                int creaturesPresent = room.getCreatures().size();
                int foodPresent = room.getFood().size();

                // Scenario 1: No adventurers present
                if (adventurersPresent == 0) {
                    noAdventurersCount++;
                }
                // Scenario 2: Fight (adventurers and creatures present)
                else if (adventurersPresent > 0 && creaturesPresent > 0) {
                    fightsCount++;
                }
                // Scenario 3: Adventurers present but no food
                else if (adventurersPresent > 0 && foodPresent == 0) {
                    adventurersNoFoodCount++;
                }
                // Scenario 4: Adventurers present with food available
                else if (adventurersPresent > 0 && foodPresent > 0) {
                    adventurersEatFoodCount++;
                }
            }

            // Ensure no more than 2 fights per turn
            assertTrue(fightsCount <= 1, "There should be no more than 1 fight per turn.");
        }

        // Output the occurrences of each scenario to verify correct behavior
        logger.info("No adventurers present count: " + noAdventurersCount);
        logger.info("Fight count: " + fightsCount);
        logger.info("Adventurers present but no food count: " + adventurersNoFoodCount);
        logger.info("Adventurers eat food count: " + adventurersEatFoodCount);


        assertTrue(fightsCount > 0, "Scenario 2: There should be at least one turn with a fight.");
    }

    // TODO
    @Test
    void test2x2PrintMaze() {

    }

}
