package edu.project2;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public final class Helper {
    private static final Random RANDOM = new Random();

    private Helper() {
    }

    public static Cell[][] createFilledMaze(int rows, int cols, Cell.Type type) {
        return IntStream.range(0, rows)
            .mapToObj(i -> IntStream.range(0, cols)
                .mapToObj(j -> new Cell(i, j, type))
                .toArray(Cell[]::new))
            .toArray(Cell[][]::new);
    }

    public static List<Cell> getUnvisitedNeighbors(Maze maze, Cell cell) {
        Cell[][] grid = maze.grid();
        int row = cell.getRow();
        int col = cell.getCol();
        List<Cell> neighbors = new ArrayList<>();

        Coordinate[] potentialNeighbors = {
            new Coordinate(row - 2, col), // North
            new Coordinate(row + 2, col), // South
            new Coordinate(row, col + 2), // East
            new Coordinate(row, col - 2)  // West
        };

        for (Coordinate neighbor : potentialNeighbors) {
            int nRow = neighbor.row();
            int nCol = neighbor.col();

            if (isWall(grid, neighbor)) {
                neighbors.add(grid[nRow][nCol]);
            }
        }

        return neighbors;
    }

    private static boolean isWall(Cell[][] grid, Coordinate coordinate) {
        int row = coordinate.row();
        int col = coordinate.col();

        return isValid(grid, coordinate) && grid[row][col].getType() == Cell.Type.WALL;
    }

    public static List<Cell> getAvailableNeighbors(Maze maze, Cell cell, Cell[][] visited) {
        Cell[][] grid = maze.grid();
        int row = cell.getRow();
        int col = cell.getCol();
        List<Cell> neighbors = new ArrayList<>();

        Coordinate[] potentialNeighbors = {
            new Coordinate(row - 1, col), // North
            new Coordinate(row + 1, col), // South
            new Coordinate(row, col + 1), // West
            new Coordinate(row, col - 1)  // East
        };

        for (Coordinate neighbor : potentialNeighbors) {
            int nRow = neighbor.row();
            int nCol = neighbor.col();

            if (isUnvisitedPassage(grid, visited, neighbor)) {
                neighbors.add(grid[nRow][nCol]);
            }
        }

        return neighbors;
    }

    private static boolean isUnvisitedPassage(Cell[][] grid, Cell[][] visited, Coordinate coordinate) {
        int row = coordinate.row();
        int col = coordinate.col();

        return isValid(grid, coordinate) && visited[row][col].getType() == Cell.Type.UNVISITED
            && grid[row][col].getType() == Cell.Type.PASSAGE;
    }

    private static boolean isValid(Cell[][] grid, Coordinate coordinate) {
        int row = coordinate.row();
        int col = coordinate.col();

        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public static <T> T chooseRandom(List<T> array) {
        return array.get(RANDOM.nextInt(array.size()));
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
        maze.grid()[midRow][midCol].setType(Cell.Type.PASSAGE);
    }
}
