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

        this.maze = new Maze(listOfRooms.size(), listOfRooms);
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
    public void testCharacterSpawn() {
        boolean characterFound = false;

        // Assign the character to room
        character.spawn(this.maze);

        Room[][] grid = maze.getGrid();
        System.out.println(Arrays.deepToString(grid));

        Room currentRoom = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                currentRoom = grid[i][j];
                if (currentRoom.hasCharacter(character)) {
                    characterFound = true;
                }
            }

            assertTrue(characterFound, "Character should be in the Maze");
        }
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

        // Move the character
        character.move(maze, currentRoomCoordinates);

        // Make sure the move was correct here
        // TODO: assert that initial position is correct
        // TODO: assert that the move was carried out correctly

    }

}
