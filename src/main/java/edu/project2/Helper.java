package edu.project2;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Helper} class provides utility methods for maze generation and solving.
 */
public final class Helper {
    private static final Random RANDOM = new Random();

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Helper() {
    }

    /**
     * Creates a filled maze with the specified number of rows and columns and cell type.
     *
     * @param rows the number of rows in the maze
     * @param cols the number of columns in the maze
     * @param type the type of the cells in the maze
     * @return a 2D array of {@link Cell} representing the filled maze
     */
    public static @NotNull Cell[][] createFilledMaze(int rows, int cols, Cell.Type type) {
        return IntStream.range(0, rows)
            .mapToObj(i -> IntStream.range(0, cols)
                .mapToObj(j -> new Cell(i, j, type))
                .toArray(Cell[]::new))
            .toArray(Cell[][]::new);
    }

    /**
     * Returns a list of unvisited neighbor cells for a given cell in a maze.
     *
     * @param maze the maze
     * @param cell the cell
     * @return a {@link List} of unvisited neighbor cells
     */
    public static @NotNull List<Cell> getUnvisitedNeighbors(@NotNull Maze maze, @NotNull Cell cell) {
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

    /**
     * Checks if a cell at a given coordinate in a grid is a wall.
     *
     * @param grid       the grid
     * @param coordinate the coordinate
     * @return true if the cell at the coordinate is a wall, false otherwise
     */
    private static boolean isWall(Cell[][] grid, @NotNull Coordinate coordinate) {
        int row = coordinate.row();
        int col = coordinate.col();

        return isValid(grid, coordinate) && grid[row][col].getType() == Cell.Type.WALL;
    }

    /**
     * Returns a list of available neighbor cells for a given cell in a maze that have not been visited.
     *
     * @param maze    the maze
     * @param cell    the cell
     * @param visited a 2D array of Cells representing the visited cells
     * @return a {@link List} of available neighbor cells that have not been visited
     */
    public static @NotNull List<Cell> getAvailableNeighbors(@NotNull Maze maze, @NotNull Cell cell, Cell[][] visited) {
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

    /**
     * Checks if a cell at a given coordinate in a grid is a passage and has not been visited.
     *
     * @param grid       the grid
     * @param visited    a 2D array of cells representing the visited cells
     * @param coordinate the coordinate
     * @return true if the cell at the coordinate is a passage and has not been visited, false otherwise
     */
    private static boolean isUnvisitedPassage(Cell[][] grid, Cell[][] visited, @NotNull Coordinate coordinate) {
        int row = coordinate.row();
        int col = coordinate.col();

        return isValid(grid, coordinate) && visited[row][col].getType() == Cell.Type.UNVISITED
            && grid[row][col].getType() == Cell.Type.PASSAGE;
    }

    /**
     * Checks if a coordinate is valid in a grid.
     *
     * @param grid       the grid
     * @param coordinate the coordinate
     * @return true if the coordinate is valid in the grid, false otherwise
     */
    private static boolean isValid(Cell[][] grid, @NotNull Coordinate coordinate) {
        int row = coordinate.row();
        int col = coordinate.col();

        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    /**
     * Chooses a random element from a list.
     *
     * @param array the list
     * @param <T>   the type of elements in the list
     * @return a randomly chosen element from the list
     */
    public static <T> T chooseRandom(@NotNull List<T> array) {
        return array.get(RANDOM.nextInt(array.size()));
    }

    /**
     * Chooses a random index from a range.
     *
     * @param length the length of the range
     * @return a randomly chosen index from the range
     */
    public static int chooseRandomIndex(int length) {
        int randomNumber = RANDOM.nextInt(length);

        if (randomNumber % 2 != 0) {
            randomNumber++;
        }

        return randomNumber == length ? randomNumber - 2 : randomNumber;
    }

    /**
     * Removes a wall between two cells in a maze.
     *
     * @param maze         the maze
     * @param currentCell  the current cell
     * @param neighborCell the neighbor cell
     */
    public static void removeWallGrid(@NotNull Maze maze, @NotNull Cell currentCell, @NotNull Cell neighborCell) {
        int midRow = currentCell.getRow() + (neighborCell.getRow() - currentCell.getRow()) / 2;
        int midCol = currentCell.getCol() + (neighborCell.getCol() - currentCell.getCol()) / 2;

        // Remove the wall between the cells
        maze.grid()[midRow][midCol].setType(Cell.Type.PASSAGE);
    }
}
