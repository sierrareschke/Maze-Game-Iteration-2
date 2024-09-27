package csci.ooad;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class PolymorphiaTest {

    private static final Logger logger = LoggerFactory.getLogger(PolymorphiaTest.class);

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

        ArrayList<Room> rooms = new ArrayList<>();
        Room roomOne = new Room("Room One");
        Room roomTwo = new Room("Room Two");
        Room roomThree = new Room("Room Three");
        Room roomFour = new Room("Room Four");
        Room roomFive = new Room("Room Five");
        Room roomSix = new Room("Room Six");
        Room roomSeven = new Room("Room Seven");
        Room roomEight = new Room("Room Eight");
        Room roomNine = new Room("Room Nine");

        rooms.add(roomOne);
        rooms.add(roomTwo);
        rooms.add(roomThree);
        rooms.add(roomFour);
        rooms.add(roomFive);
        rooms.add(roomSix);
        rooms.add(roomSeven);
        rooms.add(roomEight);
        rooms.add(roomNine);

        maze = new Maze(rooms,adventurers,creatures,foods);

        polymorphia = new Polymorphia(maze);

    }

    @Test
    void testFight() {
        Adventurer adventurer = adventurers.get(0);
        Creature creature = creatures.get(0);

        // Capture initial health values
        double initialAdventurerHealth = adventurer.getHealth();
        double initialCreatureHealth = creature.getHealth();

        // Perform the fight
        polymorphia.fight(adventurer, creature);

        // Verify that at least one character took damage
        boolean adventurerTookDamage = adventurer.getHealth() < initialAdventurerHealth;
        boolean creatureTookDamage = creature.getHealth() < initialCreatureHealth;

        assertTrue(adventurerTookDamage || creatureTookDamage, "Either the adventurer or the creature should have taken damage.");
    }

    @Test
    void testKillAdventurer() {

        Adventurer adventurer = adventurers.get(0);
        // Verify that the adventurer is in the maze initially
        assertTrue(maze.containsCharacter(adventurer), "Adventurer should be present in the maze before being killed.");

        // Call the kill method to remove the adventurer
        polymorphia.kill(adventurer);

        // Check that the adventurer has been removed from the maze
        assertFalse(maze.containsCharacter(adventurer), "Adventurer should be removed from the maze after being killed.");
    }

    @Test
    void testKillCreature() {

        Creature creature = creatures.get(0);
        // Verify that the creature is in the maze initially
        assertTrue(maze.containsCharacter(creature), "Creature should be present in the maze before being killed.");

        // Call the kill method to remove the creature
        polymorphia.kill(creature);

        // Check that the creature has been removed from the maze
        assertFalse(maze.containsCharacter(creature), "Creature should be removed from the maze after being killed.");
    }

    @Test
    void testKillInvalidCharacter() {
        // Create a new object that's not an instance of Adventurer or Creature
        Character invalidCharacter = new Character("Invalid Character",0.0);

        // Expect an IllegalStateException when trying to kill an invalid character
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            polymorphia.kill(invalidCharacter);
        });

        // Check the exception message
        assertEquals("Should be no instance of Character, cannot kill.", exception.getMessage());
    }

    @Test
    void testDetermineWinner() {
        // Test scenario where both adventurers and creatures are dead
        int result = polymorphia.determineWinner(0, 0);
        assertEquals(result ,0);

        // Test scenario where adventurers have killed all the creatures
        result = polymorphia.determineWinner(0, 1);
        assertEquals(result ,1);

        // Test scenario where creatures have killed all the adventurers
        result = polymorphia.determineWinner(1, 0);
        assertEquals(result ,2);

        // Test scenario with unexpected state (invalid input)
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            polymorphia.determineWinner(-1, -1);
        });
        assertEquals("Unexpected state: unable to determine a winner", exception.getMessage());
    }



    /* ----------- 3x3 TESTS -------------- */



    @Test
    void test3x3Init() {

        // Assertions to check the initialization
        // Check that there are 9 rooms
        assertEquals(9, maze.getNumberOfRooms(), "The maze should have 9 rooms.");

        // Check that there are 2 adventurers
        assertEquals(2, maze.getNumAdventurers(), "There should be 2 adventurers in the maze.");

        // Check that there are 5 creatures
        assertEquals(5, maze.getNumCreatures(), "There should be 5 creatures in the maze.");

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

        // Create a Maze
        maze = new Maze(rooms, adventurers, creatures, foodItems);

        // Verify that adventurers, creatures, and food are not all in the same room
        boolean differentRoomsCheck = false;
        for (Room room : maze.getRooms()) {
            if (!room.getAdventurers().isEmpty() || !room.getCreatures().isEmpty() || !room.getFood().isEmpty()) {
                differentRoomsCheck = true;
            }
        }
        assertTrue(differentRoomsCheck, "The adventurers, creatures, and food should not all be in the same room.");


    }


    @Test
    public void testPlayGame(){
        System.out.println(maze.getAdventurers());
    }

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
                boolean adventurersPresent = room.isAdventurerPresent();
                boolean creaturesPresent = room.isCreaturePresent();
                boolean foodPresent = room.isFoodPresent();

                // Scenario 1: No adventurers present
                if (!adventurersPresent) {
                    noAdventurersCount++;
                }
                // Scenario 2: Fight (adventurers and creatures present)
                else if (adventurersPresent && creaturesPresent) {
                    fightsCount++;
                }
                // Scenario 3: Adventurers present but no food
                else if (adventurersPresent && !foodPresent) {
                    adventurersNoFoodCount++;
                }
                // Scenario 4: Adventurers present with food available
                else if (adventurersPresent && foodPresent) {
                    adventurersEatFoodCount++;
                }
            }

            // Ensure no more than 2 fights per turn
            assertTrue(fightsCount <= 2, "There should be no more than 2 fights per turn.");
        }

        // Output the occurrences of each scenario to verify correct behavior
        logger.info("No adventurers present count: " + noAdventurersCount);
        logger.info("Fight count: " + fightsCount);
        logger.info("Adventurers present but no food count: " + adventurersNoFoodCount);
        logger.info("Adventurers eat food count: " + adventurersEatFoodCount);

        // Assert that all scenarios occurred at least once over multiple turns
        assertTrue(noAdventurersCount > 0, "Scenario 1: There should be at least one turn with no adventurers present.");
        assertTrue(fightsCount > 0, "Scenario 2: There should be at least one turn with a fight.");
        assertTrue(adventurersNoFoodCount > 0, "Scenario 3: There should be at least one turn with adventurers present but no food.");
        assertTrue(adventurersEatFoodCount > 0, "Scenario 4: There should be at least one turn with adventurers eating food.");
    }



    @Test
    void test3x3PrintMaze() {

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

    @Test
    void test2x2PrintMaze() {
    }

}
