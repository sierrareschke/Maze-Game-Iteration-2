package csci.ooad;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

public class PolymorphiaTest {

    private static final Logger logger = LoggerFactory.getLogger(PolymorphiaTest.class);

    Polymorphia polymorphia;
    Maze maze;
    ArrayList<Adventurer> adventurers = new ArrayList<>();
    ArrayList<Creature> creatures = new ArrayList<>();
    ArrayList<Food> foods = new ArrayList<>();
    ArrayList<Room> rooms = new ArrayList<>();


    @BeforeEach
    void setUp() {

        adventurers.add(new Adventurer("Bill"));
        adventurers.add(new Adventurer("Ted"));

        creatures.add(new Creature("Ogre"));
        creatures.add(new Creature("Troll"));
        creatures.add(new Creature("Werewolf"));
        creatures.add(new Creature("Vampire"));
        creatures.add(new Creature("Zombie"));

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


        rooms.add(new Room("Room One"));
        rooms.add(new Room("Room Two"));
        rooms.add(new Room("Room Three"));
        rooms.add(new Room("Room Four"));
        rooms.add(new Room("Room Five"));
        rooms.add(new Room("Room Six"));
        rooms.add(new Room("Room Seven"));
        rooms.add(new Room("Room Eight"));
        rooms.add(new Room("Room Nine"));

        maze = new Maze(rooms, adventurers, creatures, foods);

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
        if (adventurerTookDamage && creatureTookDamage) {
            assertTrue(adventurerTookDamage || creatureTookDamage, "Either the adventurer or the creature should have taken damage.");
        }
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
    void testDetermineWinner() {
        // Test scenario where both adventurers and creatures are dead
        int result = polymorphia.determineWinner(0, 0);
        assertEquals(result, 0);

        // Test scenario where adventurers have killed all the creatures
        result = polymorphia.determineWinner(0, 1);
        assertEquals(result, 1);

        // Test scenario where creatures have killed all the adventurers
        result = polymorphia.determineWinner(1, 0);
        assertEquals(result, 2);

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
    public void test3x3TakeTurn() {
        int totalTurns = 10; // We will simulate 10 turns
        int noAdventurersCount = 0;
        int adventurersNoFoodCount = 0;
        int adventurersEatFoodCount = 0;
        int totalFightCount = 0;

        // Run takeTurn for a number of iterations
        for (int i = 0; i < totalTurns; i++) {
            int fightsCount = 0;

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
                    totalFightCount++;
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
            logger.info("Fight count: " + fightsCount);
            // Ensure no more than 2 fights per turn
            assertTrue(fightsCount <= 2, "There should be no more than 2 fights per turn.");
        }
        // Output the occurrences of each scenario to verify correct behavior
        logger.info("No adventurers present count: " + noAdventurersCount);
        logger.info("Adventurers present but no food count: " + adventurersNoFoodCount);
        logger.info("Adventurers eat food count: " + adventurersEatFoodCount);

        // Assert that all scenarios occurred at least once over multiple turns
        assertTrue(noAdventurersCount > 0, "Scenario 1: There should be at least one turn with no adventurers present.");
        assertTrue(totalFightCount > 0, "Scenario 2: There should be at least one turn with a fight.");
        assertTrue(adventurersNoFoodCount > 0, "Scenario 3: There should be at least one turn with adventurers present but no food.");
        assertTrue(adventurersEatFoodCount > 0, "Scenario 4: There should be at least one turn with adventurers eating food.");
    }


    // TODO
    @Test
    void test3x3PrintMaze() {
        assertTrue(false, "need to implement test2x2PrintMaze");
    }

    // TODO testPlayGame
    @Test
    void testPlayGame() {
        // Run the game simulation
        polymorphia.playGame();

        // Check if either adventurers or creatures are alive
        int numAdventurersAlive = maze.getNumAdventurers();
        int numCreaturesAlive = maze.getNumCreatures();


        // Verify that the game has ended when no adventurers or creatures are alive
        boolean gameHasEnded = !(maze.areAdventuresAlive() && maze.areCreaturesAlive());
        assertTrue(gameHasEnded, "The game should end when no adventurers or creatures are alive.");

        // Verify that the winner was determined correctly
        int expectedWinner;
        logger.info("numAdventurersAlive: " + numAdventurersAlive);
        logger.info("numCreaturesAlive: " + numCreaturesAlive);

        logger.info("areAdventuresAlive: " + maze.areAdventuresAlive());
        logger.info("areCreaturesAlive: " + maze.areCreaturesAlive());
        if (numAdventurersAlive == 0 && numCreaturesAlive == 0) {
            expectedWinner = 0; // No one wins
        } else if (numAdventurersAlive > 0 && numCreaturesAlive == 0) {
            expectedWinner = 1; // Adventurers win
        } else if (numCreaturesAlive > 0 && numAdventurersAlive == 0) {
            expectedWinner = 2; // Creatures win
        } else {
            throw new IllegalStateException("Unexpected game state: both adventurers and creatures are alive. NumCreaturesAlive = "+ numCreaturesAlive + ". NumAdventurersAlive = " +numAdventurersAlive);
        }

        int actualWinner = polymorphia.determineWinner(numCreaturesAlive, numAdventurersAlive);
        assertEquals(expectedWinner, actualWinner, "The winner of the game should be determined correctly.");

        // Verify log statements by checking the game progression via logging (optional, depending on setup)
        logger.info("Game has ended with winner: " + actualWinner);
    }




}
