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
    private Maze maze;
    private Character character;
    private Adventurer adventurer;
    private Food food;

    private static final Logger logger = LoggerFactory.getLogger(Character.class);
    private List<Room> listOfRooms = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        character = new Creature("TestCreature");
        adventurer  = new Adventurer("TestAdventurer");
        food = new Food("Hotdog");
        List<Creature> listOfCreatures = List.of((Creature) character);
        List<Adventurer> listOfAdventurers = List.of(adventurer);
        List<Food> listOfFoods = List.of(food);

        //        Character testCharacter = new Character("TestCharacter");
        // Create the maze
        String[] roomNames = {"Room NW", "Room N", "Room NE", "Room W", "Room C", "Room E", "Room SW", "Room S", "Room SE"};
        int roomIndex = 0;
        for (String name: roomNames){
            listOfRooms.add(new Room(name, roomIndex));
            roomIndex++;
        }

        this.maze = new Maze(listOfRooms, listOfAdventurers, listOfCreatures, listOfFoods);
    }

    @Test
    public void testNewMazeCreation(){
        character = new Creature("TestCreature");
        adventurer  = new Adventurer("TestAdventurer");
        food = new Food("Hotdog");
        List<Creature> listOfCreatures = List.of((Creature) character);
        List<Adventurer> listOfAdventurers = List.of(adventurer);
        List<Food> listOfFoods = List.of(food);
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

        this.maze = new Maze(listOfRooms, listOfAdventurers, listOfCreatures, listOfFoods);

        assertNotEquals(0, maze.getNumberOfRooms());
        assertEquals(3, maze.getGrid().length, "Matrix should have 3 rows");

        //TODO: Remove this later...leaving it in for testing
        logger.info(Arrays.deepToString(maze.getGrid()));
    }

    @Test
    public void testGetRooms(){
        List<String> originalRoomNames = new ArrayList<String>();
        List<String> fetchedRoomNames = new ArrayList<String>();

        ArrayList<Room> allFetchedRooms = maze.getRooms();
        System.out.println(allFetchedRooms);

        for (Room room: allFetchedRooms){
            fetchedRoomNames.add(room.getName());
        }

        for (Room room: this.listOfRooms){
            System.out.println(room.getName());
            originalRoomNames.add(room.getName());
        }

        assertEquals(originalRoomNames, fetchedRoomNames);
    }
}
