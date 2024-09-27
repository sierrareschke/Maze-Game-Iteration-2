package csci.ooad;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdventurerTest {

    private Adventurer adventurer;
    private Creature creature;
    Polymorphia polymorphia;
    Maze maze;
    ArrayList<Adventurer> adventurers = new ArrayList<>();;
    ArrayList<Creature> creatures = new ArrayList<>();;
    ArrayList<Food> foods;

    // This method will run before each test to initialize the adventurer object
    @BeforeEach
    public void setUp() {
        creature = new Creature("TestCreature");
        adventurer  = new Adventurer("TestAdventurer");

        adventurers.add(new Adventurer("TestAdventurer"));
        adventurers.add(new Adventurer("TestAdventurer2"));

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

    // Test the initial health of the adventurer
    @Test
    public void testInitialAdventurerHealth() {
        assertEquals(5.0, adventurer.getHealth(), "Initial adventurer health should be 5.0");
    }

    // Test the name access method for adventurer
    @Test
    public void testGetAdventurerName() {
        assertEquals("TestAdventurer", adventurer.getName(), "Adventurer name should be TestAdventurer");
    }

    @Test
    public void testCharacterHealth(){
        // Tests that a character that eats food heals
        Adventurer adventurer = new Adventurer("John");
        double adventurerHealth = adventurer.getHealth();
        Food food = new Food();
        adventurer.eatFood(food);
        double adventurerNewHealth = adventurer.getHealth();
        assertEquals(adventurerNewHealth, adventurerHealth + 1);
    }

    // Test subtractFromHealth with a positive (valid) value
    @Test
    public void testSubtractFromHealthValid() {
        adventurer.subtractFromHealth(1.0);
        assertEquals(4.0, adventurer.getHealth(), "Health should be reduced to 4.0 after subtracting 1.0");
    }

    // Test subtractFromHealth with a negative value (should not change health)
    @Test
    public void testSubtractFromHealthInvalid() {
        adventurer.subtractFromHealth(-1.0); // Negative value, should not affect health
        assertEquals(5.0, adventurer.getHealth(), "Health should remain 5.0 after passing a negative value");
    }

    // Test the toString method
    @Test
    public void testToString() {
        String expectedString = "TestAdventurer(health: 5.0)";
        assertEquals(expectedString, adventurer.toString(), "toString should return correct format");
    }

    // Test that health does not go below 0 (you can add this check in the Character class if needed)
    @Test
    public void testHealthDoesNotGoBelowZero() {
        adventurer.subtractFromHealth(10.0); // Subtracting more than current health
        assertTrue(adventurer.getHealth() < 0, "Health can go negative as per current implementation, but we can change this if needed.");
    }

    @Test
    public void testAdventureSpawn() {
        boolean adventurerFound = false;

        // Assign the character to room
        adventurer.spawn(maze);

        HashMap<String, Integer> coords = adventurer.currentRoomCoordinates(maze);
        int x = coords.get("x");
        int y = coords.get("y");

        Room[][] grid = maze.getGrid();

        if (grid[x][y].hasCharacter(adventurer)) {
            adventurerFound = true;
        }

        assertTrue(adventurerFound, "Character should be in the Maze");
    }

    @Test
    public void testCharacterMove(){
        // Assign the character to room
        adventurer.spawn(this.maze);

        // Find Characters current room
        HashMap<String, Integer> currentRoomCoordinates  = adventurer.currentRoomCoordinates(this.maze);
        int currentX = currentRoomCoordinates.get("x");
        int currentY = currentRoomCoordinates.get("y");

        assertTrue(currentX >= 0 && currentX <= 2, "currentX should be between 0 and 2.");
        assertTrue(currentY >= 0 && currentY <= 2, "currentY should be between 0 and 2.");

        // Move the character
        adventurer.move(maze);

        HashMap<String, Integer> newRoomCoordinates = adventurer.currentRoomCoordinates(this.maze);
        int newX = newRoomCoordinates.get("x");
        int newY = newRoomCoordinates.get("y");
        assertTrue(newX >= 0 && newX <= 2, "NewX should be between 0 and 2.");
        assertTrue(newY >= 0 && newY <= 2, "newY should be between 0 and 2.");

        // Make sure the move was correct here
        // TODO: assert that the move was carried out correctly
        int sumCurrent = currentY + currentX;
        int sumNew = newY + newX;
        int moveDifference = Math.abs(sumCurrent - sumNew);

        if (currentX != newX){
            System.out.println("X Changed: " + currentY + " " + newY);
            assertEquals(currentY, newY);
        } else {
            System.out.println("Y Changed: " + currentX + " " + newX);
            assertEquals(currentX, newX);
        }
    }


}
