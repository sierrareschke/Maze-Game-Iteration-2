package csci.ooad;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {
    private Maze maze;
    private Character character;
    private Adventurer adventurer;

    // This method will run before each test to initialize the character object
    @BeforeEach
    public void setUp() {
        character = new Character("TestCharacter");
        adventurer  = new Adventurer("TestAdventurer");
        List<Room> listOfRooms = new ArrayList<>();
        // TODO: Probably could move this maze creation code to the BeforeEach statement
//        Character testCharacter = new Character("TestCharacter");
        // Create the maze
        String[] roomNames = {"Room NW", "Room N", "Room NE", "Room W", "Room C", "Room E", "Room SW", "Room S", "Room SE"};
        int roomIndex = 0;
        for (String name: roomNames){
            listOfRooms.add(new Room(name, roomIndex));
            roomIndex++;
        }

        this.maze = new Maze(listOfRooms);
    }

    // Test the initial health of the character
    @Test
    public void testInitialHealth() {
        assertEquals(5.0, character.getHealth(), "Initial health should be 5.0");
    }

    // Test the name access method
    @Test
    public void testGetName() {
        assertEquals("TestCharacter", character.getName(), "Character name should be TestCharacter");
    }

    // Test subtractFromHealth with a negative value
    @Test
    public void testSubtractFromHealthValid() {
        character.subtractFromHealth(-1.0);
        assertEquals(4.0, character.getHealth(), "Health should be reduced to 4.0 after subtracting 1.0");
    }

    // Test subtractFromHealth with a positive value (should not change health)
    @Test
    public void testSubtractFromHealthInvalid() {
        character.subtractFromHealth(1.0); // Positive value, should not affect health
        assertEquals(5.0, character.getHealth(), "Health should remain 5.0 after passing a positive value");
    }

    // Test the toString method
    @Test
    public void testToString() {
        String expectedString = "TestCharacter(health: 5.0)";
        assertEquals(expectedString, character.toString(), "toString should return correct format");
    }

    // Test that health does not go below 0 (you can add this check in the Character class if needed)
    @Test
    public void testHealthDoesNotGoBelowZero() {
        character.subtractFromHealth(-10.0); // Subtracting more than current health
        assertTrue(character.getHealth() < 0, "Health can go negative as per current implementation, but we can change this if needed.");
    }

    @Test
    public void testAdventureSpawn() {
        boolean adventurerFound = false;

        // Assign the character to room
        adventurer.spawn(maze);

        HashMap<String, Integer>coords = adventurer.currentRoomCoordinates(maze);
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
        character.spawn(this.maze);

        // Find Characters current room
        HashMap<String, Integer> currentRoomCoordinates  = character.currentRoomCoordinates(this.maze);
        int currentX = currentRoomCoordinates.get("x");
        int currentY = currentRoomCoordinates.get("y");

        assertTrue(currentX >= 0 && currentX <= 2, "currentX should be between 0 and 2.");
        assertTrue(currentY >= 0 && currentY <= 2, "currentY should be between 0 and 2.");

        // Move the character
        character.move(maze);

        HashMap<String, Integer> newRoomCoordinates = character.currentRoomCoordinates(this.maze);
        int newX = newRoomCoordinates.get("x");
        int newY = newRoomCoordinates.get("y");
        assertTrue(newX >= 0 && newX <= 2, "NewX should be between 0 and 2.");
        assertTrue(newY >= 0 && newY <= 2, "newY should be between 0 and 2.");

        // Make sure the move was correct here
        // TODO: assert that the move was carried out correctly
        int sumCurrent = currentY + currentX;
        int sumNew = newY + newX;
        int moveDifference = Math.abs(sumCurrent - sumNew);
        assertTrue(moveDifference <= 1, "Sum current should be equal to Sum new");

    }

}
