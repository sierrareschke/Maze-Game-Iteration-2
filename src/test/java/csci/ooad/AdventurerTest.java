package csci.ooad;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdventurerTest {

    private Adventurer adventurer;
    private Polymorphia polymorphia;
    private Maze maze;
    private ArrayList<Adventurer> adventurers;
    private ArrayList<Creature> creatures;
    private ArrayList<Food> foods;
    List<Room> rooms;

    // This method will run before each test to initialize the adventurer object

    @BeforeEach
    void setUp() {
        adventurer = new Adventurer("TestAdventurer");

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

        maze = new Maze(rooms,adventurers,creatures,foods);

    }


    // Test the initial health of the adventurer
    @Test
    public void testInitialHealth() {
        assertEquals(5.0, adventurer.getHealth(), "Initial adventurer health should be 5.0");
    }

    // Test the name access method for adventurer
    @Test
    public void testGetName() {
        assertEquals("TestAdventurer", adventurer.getName(), "Adventurer name should be TestAdventurer");
    }

    @Test
    public void testCharacterHeal(){
        // Tests that a character that eats food heals
        Adventurer adventurer = new Adventurer("John");
        double adventurerHealth = adventurer.getHealth();
        Food food = new Food();
        adventurer.eatFood(food, maze);
        double adventurerNewHealth = adventurer.getHealth();
        assertEquals(adventurerNewHealth, adventurerHealth + 1);
    }
}
