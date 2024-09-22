package csci.ooad;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    List<Room> listOfRooms = new ArrayList<>();

    @Test
    public void testNewMazeCreation(){
        String[] roomNames = {"Room NW", "Room N", "Room NE", "Room W", "Room C", "Room E", "Room SW", "Room S", "Room SE"};

        int roomIndex = 0;
        for (String name: roomNames){
            listOfRooms.add(new Room(name,roomIndex));
            roomIndex++;
        }

        Maze maze = new Maze(listOfRooms.size(), listOfRooms);

        assertNotEquals(0, maze.numberOfRooms);
        assertNotEquals(false, maze.isSquareMatrix);
        assertEquals(3, maze.grid.length, "Matrix should have 3 rows");

        //TODO: Remove this later...leaving it in for testing
        System.out.println(Arrays.deepToString(maze.grid));
    }

    @Test
    public void testInitializeRooms() {

        // Create the maze grid
        Maze maze = new Maze(listOfRooms.size(), listOfRooms);


    }
}
