package edu.project2;

import edu.project2.generators.GrowingTreeGenerator;
import edu.project2.generators.RecursiveBacktrackerGenerator;
import edu.project2.solvers.BfsSolver;
import edu.project2.solvers.DfsSolver;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project2.Helper.createFilledMaze;

/**
 * Tests for Project 2
 */
class Project2Test {
    @Test
    @DisplayName("GrowingTreeGenerator should generate a maze with fewer walls than cells")
    void growingTreeGenerator_TestGenerate() {
        // Arrange
        int height = 19;
        int width = 79;
        var generator = new GrowingTreeGenerator();

        // Act
        Maze maze = generator.generate(height, width);
        long wallCount = maze.getCells().stream()
            .filter(cell -> cell.getType() == Cell.Type.WALL)
            .count();

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(maze),
            () -> Assertions.assertEquals(height, maze.grid().length),
            () -> Assertions.assertEquals(width, maze.grid()[0].length),
            () -> Assertions.assertTrue(wallCount < height * width)
        );
    }

    @Test
    @DisplayName("RecursiveBacktrackerGenerator should generate a maze with fewer walls than cells")
    void recursiveBacktrackerGenerator_TestGenerate() {
        // Arrange
        int height = 19;
        int width = 79;
        var generator = new RecursiveBacktrackerGenerator();

        // Act
        Maze maze = generator.generate(height, width);
        long wallCount = maze.getCells().stream()
            .filter(cell -> cell.getType() == Cell.Type.WALL)
            .count();

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(maze),
            () -> Assertions.assertEquals(height, maze.grid().length),
            () -> Assertions.assertEquals(width, maze.grid()[0].length),
            () -> Assertions.assertTrue(wallCount < height * width)
        );
    }

    @Test
    @DisplayName("BfsSolver should find a valid path from start to end in a generated maze")
    void bfsSolver_TestSolve() {
        // Arrange
        int height = 19;
        int width = 79;
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(height - 1, width - 1);
        var generator = new GrowingTreeGenerator();
        var solver = new BfsSolver();

        // Act
        Maze maze = generator.generate(height, width);
        List<Coordinate> path = solver.solve(maze, start, end);

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(maze),
            () -> Assertions.assertNotNull(path),
            () -> Assertions.assertFalse(path.isEmpty()),
            () -> Assertions.assertEquals(start, path.get(path.size() - 1)),
            () -> Assertions.assertEquals(end, path.get(0)),
            () -> {
                for (int i = 0; i < path.size() - 1; i++) {
                    Coordinate current = path.get(i);
                    Coordinate next = path.get(i + 1);

                    Assertions.assertTrue(isNeighbor(current, next));
                }
            }
        );
    }

    @Test
    @DisplayName("BfsSolver should return an empty path when there is no valid path from start to end in a maze.")
    void bfsSolver_TestSolveNoPath() {
        // Arrange
        int height = 19;
        int width = 79;
        Cell[][] grid = createFilledMaze(height, width, Cell.Type.WALL);
        Maze maze = new Maze(height, width, grid);
        var solver = new BfsSolver();

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(height - 1, width - 1);

        List<Coordinate> path = solver.solve(maze, start, end);

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(path),
            () -> Assertions.assertTrue(path.isEmpty())
        );
    }

    @Test
    @DisplayName("DfsSolver should find a valid path from start to end in a generated maze")
    void dfsSolver_TestSolve() {
        // Arrange
        int height = 19;
        int width = 79;
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(height - 1, width - 1);
        var generator = new GrowingTreeGenerator();
        var solver = new DfsSolver();

        // Act
        Maze maze = generator.generate(height, width);
        List<Coordinate> path = solver.solve(maze, start, end);

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(maze),
            () -> Assertions.assertNotNull(path),
            () -> Assertions.assertFalse(path.isEmpty()),
            () -> Assertions.assertEquals(start, path.get(path.size() - 1)),
            () -> Assertions.assertEquals(end, path.get(0)),
            () -> {
                for (int i = 0; i < path.size() - 1; i++) {
                    Coordinate current = path.get(i);
                    Coordinate next = path.get(i + 1);

                    Assertions.assertTrue(isNeighbor(current, next));
                }
            }
        );
    }

    @Test
    @DisplayName("DfsSolver should return an empty path when there is no valid path from start to end in a maze.")
    void dfsSolver_TestSolveNoPath() {
        // Arrange
        int height = 19;
        int width = 79;
        Cell[][] grid = createFilledMaze(height, width, Cell.Type.WALL);
        Maze maze = new Maze(height, width, grid);
        var solver = new DfsSolver();

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(height - 1, width - 1);

        List<Coordinate> path = solver.solve(maze, start, end);

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(path),
            () -> Assertions.assertTrue(path.isEmpty())
        );
    }

    @Test
    @DisplayName("Maze equals method should correctly compare maze")
    void maze_TestEquals() {
        // Arrange
        int height = 10;
        int width = 10;
        Cell[][] grid = new Cell[height][width];
        Maze maze = new Maze(height, width, grid);
        Maze maze2 = new Maze(height, width, grid);
        Maze maze3 = new Maze(height + 1, width, grid);
        Maze maze4 = new Maze(height, width + 1, grid);
        Maze maze5 = new Maze(height, width, new Cell[height][width]);

        // Act & Assert
        //noinspection EqualsWithItself,AssertBetweenInconvertibleTypes
        Assertions.assertAll(
            () -> Assertions.assertEquals(maze, maze2),
            () -> Assertions.assertNotEquals(maze, maze3),
            () -> Assertions.assertNotEquals(maze, maze4),
            () -> Assertions.assertNotEquals(maze, grid),
            () -> Assertions.assertEquals(maze, maze5),
            () -> Assertions.assertEquals(maze, maze)
        );
    }

    @Test
    @DisplayName("Maze hashCode method should generate consistent and distinct hash codes")
    void maze_TestHashCode() {
        // Arrange
        int height = 10;
        int width = 10;
        Cell[][] grid = new Cell[height][width];
        Maze maze = new Maze(height, width, grid);
        Maze maze2 = new Maze(height, width, grid);
        Maze maze3 = new Maze(height + 1, width, grid);
        Maze maze4 = new Maze(height, width + 1, grid);
        Maze maze5 = new Maze(height, width, new Cell[height][width]);

        // Act & Assert
        //noinspection ArrayHashCode
        Assertions.assertAll(
            () -> Assertions.assertEquals(maze.hashCode(), maze2.hashCode()),
            () -> Assertions.assertNotEquals(maze.hashCode(), maze3.hashCode()),
            () -> Assertions.assertNotEquals(maze.hashCode(), maze4.hashCode()),
            () -> Assertions.assertNotEquals(maze.hashCode(), grid.hashCode()),
            () -> Assertions.assertEquals(maze.hashCode(), maze5.hashCode()),
            () -> Assertions.assertEquals(maze.hashCode(), maze.hashCode())
        );
    }

    @Test
    @DisplayName("Maze toString method should return a string representation with height, width, and grid information")
    void testToString() {
        // Arrange
        int height = 10;
        int width = 10;
        Cell[][] grid = new Cell[height][width];
        Maze maze = new Maze(height, width, grid);
        String expected = "Maze[height=" + height + ", width=" + width + ", grid=" + Arrays.deepToString(grid) + "]";

        // Act & Assert
        Assertions.assertEquals(expected, maze.toString());
    }

    private boolean isNeighbor(Coordinate a, Coordinate b) {
        return Math.abs(a.row() - b.row()) + Math.abs(a.col() - b.col()) == 1;
    }
}
