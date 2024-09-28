package csci.ooad;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RoomTest {

    private Room room;
    private Adventurer adventurer;
    private Creature creature;

    // create room, adventurer, creature objects before each test
    @BeforeEach
    public void setUp() {
        adventurer = new Adventurer("TestAdventurer");
        creature = new Creature("TestCreature");
    }

    // Method to test the Room constructor by checking the name and index
    @Test
    public void testConstructor() {
        room = new Room("TestRoom");
        assertEquals("TestRoom", room.getName(), "Room name should be TestRoom");
    }


    // Method to test if occupants are successfully added to Rooms
    @Test
    public void testAddOccupant() {
        room = new Room();
        room.addOccupant(adventurer);
        assertEquals(1, room.getOccupants().size(), "Room should have 1 occupant after adding an adventurer.");

        room.addOccupant(creature);
        assertEquals(2, room.getOccupants().size(), "Room should have 2 occupants after adding a creature.");
    }

    // Method to test if occupants are correctly removed from a room
    @Test
    public void testRemoveAdventurer() {
        room = new Room();

        // add adventurer and creature
        room.addOccupant(adventurer);
        room.addOccupant(creature);

        // Remove the adventurer
        room.removeCharacter(adventurer);
        assertEquals(1, room.getOccupants().size(), "Room should have 1 occupant after removing the adventurer.");
    }


    // Method to test the toString method for Room, ensuring correct output
    @Test
    public void testToString() {
        // Test empty room case
        Room emptyRoom = new Room("Northwest");
        String expectedEmptyRoomOutput = "Northwest:\n" +
                "\tAdventurers: \n" +
                "\tCreatures: \n" +
                "\tFood: \n";
        assertEquals(expectedEmptyRoomOutput, emptyRoom.toString(), "Empty room description should match.");
    }

    @Test void testToStringOneOccupant() {
        // Test room with occupants case
        Room occupiedRoom = new Room("Southeast");
        Adventurer adventurer = new Adventurer("Test Adventurer");
        occupiedRoom.addOccupant(adventurer);

        String expectedOccupiedRoomOutput = "Southeast:\n" +
                "\tAdventurers: Test Adventurer(health: 5.0). \n" +
                "\tCreatures: \n" +
                "\tFood: \n";
        assertEquals(expectedOccupiedRoomOutput, occupiedRoom.toString(), "Occupied room description should match.");
    }

    @Test
    public void testToStringMultipleOccupants() {
        Room room = new Room("East");
        Adventurer adventurer = new Adventurer("Test Adventurer");
        Creature creature = new Creature("Test Creature");
        room.addOccupant(adventurer);
        room.addOccupant(creature);

        String expectedOutput = "East:\n" +
                "\tAdventurers: Test Adventurer(health: 5.0). \n" +
                "\tCreatures: Test Creature(health: 3.0). \n" +
                "\tFood: \n";
        assertEquals(expectedOutput, room.toString(), "Room description with multiple occupants should match.");
    }


    @Test
    public void testOccupantsEmpty(){
        Room room = new Room("NotEmpty");
        boolean emptyOrNot = room.isEmpty();
        assertTrue(emptyOrNot);
    }

    @Test
    public void testIsFoodPresent(){
        Room room = new Room();
        room.addFood(new Food("Hot Dog"));
        assertTrue(room.isFoodPresent());
    }

    @Test
    public void testEmptyRoom() {
        Room room = new Room("Test Room");
        Adventurer adventurer = new Adventurer("Test Adventurer");
        Creature creature = new Creature("Test Creature");

        room.addOccupant(adventurer);
        room.addOccupant(creature);

        // Empty the room
        room.emptyRoom();
        assertTrue(room.getOccupants().isEmpty());
    }

    // Test that the occupants (adventurers and creatures) are returned in sorted order by health
    @Test
    public void testOccupantsSortedByHealth() {
        room = new Room("Test Room");

        Adventurer adventurer1 = new Adventurer("Bill");   // initial health = 5.0
        Adventurer adventurer2 = new Adventurer("Tim");  // initial health = 5.0
        Creature creature1 = new Creature("Goblin");        // initial health = 3.0
        Creature creature2 = new Creature("Dragon");        // initial health = 3.0

        // Modify health values using addToHealth and subtractFromHealth
        adventurer1.subtractFromHealth(2.0);  // Zelda's health is now 3.0
        adventurer2.subtractFromHealth(1.0);  // Aragon's health is now 4.0
        creature1.addToHealth(1.0);           // Goblin's health is now 4.0
        creature2.addToHealth(2.0);           // Dragon's health is now 5.0

        // Add occupants in a random order
        room.addOccupant(adventurer2);
        room.addOccupant(creature2);
        room.addOccupant(adventurer1);
        room.addOccupant(creature1);

        // Retrieve occupants sorted by health
        List<Adventurer> adventurers = room.getAdventurers(); // should be sorted by health
        List<Creature> creatures = room.getCreatures(); // should be sorted by health

        // Check if adventurers are sorted by health in ascending order
        assertEquals("Bill", adventurers.get(0).getName(), "First occupant should be Bill (health: 3.0).");
        assertEquals("Tim", adventurers.get(1).getName(), "Second occupant should be Tim (health: 4.0).");

        assertEquals("Goblin", creatures.get(0).getName(), "Third occupant should be Goblin (health: 4.0).");
        assertEquals("Dragon", creatures.get(1).getName(), "Fourth occupant should be Dragon (health: 5.0).");
    }


}
