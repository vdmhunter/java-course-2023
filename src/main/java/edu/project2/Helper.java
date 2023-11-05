package edu.project2;

import edu.project2.types.Cell;
import edu.project2.types.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Helper {
    private static final Random RANDOM = new Random();

    private Helper() {
    }

    public static Cell[][] createFilledMaze(int rows, int cols, Cell.Type type) {
        Cell[][] matrix = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new Cell(i, j, type);
            }
        }

        return matrix;
    }

    public static List<Cell> getUnvisitedNeighbors(Maze maze, Cell cell) {
        Cell[][] grid = maze.getGrid();
        int row = cell.getRow();
        int col = cell.getCol();
        List<Cell> neighbors = new ArrayList<>();

        int[][] potentialNeighbors = {
            {row - 2, col}, // West
            {row + 2, col}, // East
            {row, col + 2}, // South
            {row, col - 2}  // North
        };

        for (int[] neighbor : potentialNeighbors) {
            if (neighbor[0] >= 0 && neighbor[0] < grid.length
                && neighbor[1] >= 0 && neighbor[1] < grid[0].length
                && grid[neighbor[0]][neighbor[1]].getType() == Cell.Type.WALL) {
                neighbors.add(grid[neighbor[0]][neighbor[1]]);
            }
        }

        return neighbors;
    }

    public static List<Cell> getAvailableNeighbors(Maze maze, Cell cell, Cell[][] visited) {
        Cell[][] grid = maze.getGrid();
        int row = cell.getRow();
        int col = cell.getCol();
        List<Cell> neighbors = new ArrayList<>();

        int[][] potentialNeighbors = {
            {row - 1, col}, // West
            {row + 1, col}, // East
            {row, col + 1}, // South
            {row, col - 1}  // North
        };

        for (int[] neighbor : potentialNeighbors) {
            if (neighbor[0] >= 0 && neighbor[0] < grid.length
                && neighbor[1] >= 0 && neighbor[1] < grid[0].length
                && visited[neighbor[0]][neighbor[1]].getType() == Cell.Type.UNVISITED
                && grid[neighbor[0]][neighbor[1]].getType() == Cell.Type.PASSAGE) {
                neighbors.add(grid[neighbor[0]][neighbor[1]]);
            }
        }

        return neighbors;
    }

    public static <T> T chooseRandom(List<T> array) {
        int randomIndex = RANDOM.nextInt(array.size());

        return array.get(randomIndex);
    }

    public static int chooseRandomIndex(int length) {
        int randomNumber = RANDOM.nextInt(length);

        if (randomNumber % 2 != 0) {
            randomNumber++;
        }

        return randomNumber == length ? randomNumber - 2 : randomNumber;
    }

    public static void removeWallGrid(Maze maze, Cell currentCell, Cell neighborCell) {
        int midRow = currentCell.getRow() + (neighborCell.getRow() - currentCell.getRow()) / 2;
        int midCol = currentCell.getCol() + (neighborCell.getCol() - currentCell.getCol()) / 2;

        // Remove the wall between the cells
        maze.getGrid()[midRow][midCol].setType(Cell.Type.PASSAGE);
    }
}
