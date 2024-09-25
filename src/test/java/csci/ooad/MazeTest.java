package csci.ooad;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    private static final Logger logger = LoggerFactory.getLogger(Character.class);
    List<Room> listOfRooms = new ArrayList<>();

    @Test
    public void testNewMazeCreation(){
        String[] roomNames = {"Room NW", "Room N", "Room NE", "Room W", "Room C", "Room E", "Room SW", "Room S", "Room SE"};

        int roomIndex = 0;
        for (String name: roomNames){
            listOfRooms.add(new Room(name));
            roomIndex++;
        }

        Maze maze = new Maze(listOfRooms);

        assertNotEquals(0, maze.getNumberOfRooms());
        assertEquals(3, maze.getGrid().length, "Matrix should have 3 rows");

        //TODO: Remove this later...leaving it in for testing
        logger.info(Arrays.deepToString(maze.getGrid()));
    }

    @Test
    public void testInitializeRooms() {

        // Create the maze grid
        Maze maze = new Maze(listOfRooms);


    }
}
