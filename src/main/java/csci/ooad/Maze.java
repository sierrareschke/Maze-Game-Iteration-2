package csci.ooad;

import java.util.List;
import java.util.Objects;

public class Maze {
    private int numberOfRooms = 0;
    private boolean isSquareMatrix = false;
    private Room[][] grid = null;

    // Constructor for the maze
    Maze(int numberOfRooms, List<Room> listOfRooms) {
        this.isSquareMatrix = canFormSquareMatrix(numberOfRooms);
        this.numberOfRooms = numberOfRooms;
        this.grid = initializeRooms(listOfRooms);

    }

    private Room[][] initializeRooms(List<Room> listOfRooms){
        if (isSquareMatrix){
            // Find the square root to determine matrix size
            int mazeDimensions = (int) Math.sqrt(this.numberOfRooms);
            // initialize the 2D array
            Room[][] matrix = new Room[mazeDimensions][mazeDimensions];
            for (int i = 0; i < listOfRooms.size(); i++) {
                // writes the rooms to the grid in order
                matrix[i/mazeDimensions][i%mazeDimensions] = listOfRooms.get(i);
            }
            return matrix;
        } else return null;

    }

    // Helper methods
    private static boolean canFormSquareMatrix(int value) {
        // Calculate the square root of the value
        double sqrt = Math.sqrt(value);

        // Check if the square root is an integer (i.e., sqrt == floor(sqrt))
        return sqrt == Math.floor(sqrt);
    }

    // Getters
    public int getNumberOfRooms() {
        return numberOfRooms;
    }
    public boolean isSquareMatrix() {
        return isSquareMatrix;
    }
    public Room[][] getGrid() {
        return grid;
    }
    
    public Room getRoomInGrid(int x, int y){
        return grid[x][y];
    }


//    public void updateGrid(Maze maze, Room room ){
//        Room[][] grid = maze.getGrid();
//
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                Room currentRoom = grid[i][j];
//                if (Objects.equals(currentRoom.getName(), room.getName())){
//                    grid[i][j] = room;
//                }
//            }
//        }
//    }
}
