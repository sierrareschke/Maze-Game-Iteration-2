package csci.ooad;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    int numberOfRooms = 9;
    List<Room> listOfRooms = new ArrayList<>();

    @Test
    public void testNewMazeCreation(){

        // Create and assign rooms to the respective positions
        listOfRooms.add(new Room("Room NW"));  // Empty occupants for now
        listOfRooms.add(new Room("Room N"));
        listOfRooms.add(new Room("Room NE"));
        listOfRooms.add(new Room("Room W"));
        listOfRooms.add(new Room("Room C"));
        listOfRooms.add(new Room("Room E"));
        listOfRooms.add(new Room("Room SW"));
        listOfRooms.add(new Room("Room S"));
        listOfRooms.add(new Room("Room SE"));

        Maze maze = new Maze(numberOfRooms, listOfRooms);

        assertNotEquals(0, maze.numberOfRooms);
        assertNotEquals(false, maze.isSquareMatrix);
        System.out.println(Arrays.deepToString(maze.grid));
        // Need to assert that the maze is the correct dimesions
    }

    @Test
    public void testInitializeRooms() {

        // Create the maze grid
        Maze maze = new Maze(listOfRooms.size(), listOfRooms);


    }
}
