package csci.ooad;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

public class PolymorphiaTest {

    Polymorphia polymorphia;
    Maze maze;


    @BeforeEach
    void setUp() {
        // Create 2 adventurers
        Adventurer adventurerOne = new Adventurer("Bill");
        Adventurer adventurerTwo = new Adventurer("Ted");

        ArrayList<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(adventurerOne);
        adventurers.add(adventurerTwo);

        // Create 5 creatures
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(new Creature("Goblin"));
        creatures.add(new Creature("Orc"));
        creatures.add(new Creature("Troll"));
        creatures.add(new Creature("Dragon"));
        creatures.add(new Creature("Imp"));

        // Create 10 food items
        ArrayList<Food> foodItems = new ArrayList<>();
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

        // Create 9 rooms
        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            rooms.add(new Room("Room " + i));
        }

        // Create a Maze
        maze = new Maze(rooms,adventurers,creatures,foodItems);

        // Initialize Polymorphia game
        Polymorphia polymorphiaGame = new Polymorphia(maze);
    }

    // TODO - REST OF POLYMORPHIA (GRACE)




    // TODO - testTakeTurn method partially updated for hw3 implementation
    // testTakeTurn : Tests that if characters in same room, fight. If not, move adventurer called
//    @Test
//    public void testTakeTurn() {
//        // Initialize adventurer and creature
//        Adventurer adventurer1 = new Adventurer("Test Adventurer 1");
//        Adventurer adventurer2 = new Adventurer("Test Adventurer 2");
//        Adventurer[] adventurers = new Adventurer[0];
//        adventurers[0] = adventurer1;
//        adventurers[1] = adventurer2;
//
//
//        Creature creature = new Creature("Test Zombie");
//        Creature[] creatures = new Creature[0];
//        creatures[0] = creature;
//
//        // Test scenario where an adventurer and creature are in the same room (should trigger a fight)
//        Room room = testPolymorphia.getRooms()[0];  // Place a creature and adventurer in the same room
//        room.addOccupant(adventurers[0]);
//        room.addOccupant(creatures[0]);
//
//        // Call takeTurn (this should trigger a fight between adventurer1 and creature)
//        testPolymorphia.takeTurn(adventurers, creatures);
//
//        // Both should lose at least 0.5 health due to the fight
//        assertTrue(adventurers[0].getHealth() < 5.0, "Adventurer should lose at least 0.5 health.");
//        assertTrue(creatures[0].getHealth() < 5.0, "Creature should lose at least 0.5 health.");
//
//        // Test scenario where adventurer and creature are in different rooms (should move the adventurer)
//        room.emptyRoom();
//        Room adventurerRoom = testPolymorphia.getRooms()[0];
//        Room createureRoom = testPolymorphia.getRooms()[1];
//        adventurerRoom.addOccupant(adventurers[0]);
//        createureRoom.addOccupant(creatures[0]);
//
//        // Call takeTurn (this should trigger the adventurer to move)
//        testPolymorphia.takeTurn(adventurers, creatures);
//
//        // Verify that the adventurer has moved from the original room
//        Room adventurerNewRoom = testPolymorphia.getRoomOfCharacter(adventurers[0]);
//        assertNotEquals(room, adventurerNewRoom, "Adventurer should have moved to a neighboring room.");
//    }





}
