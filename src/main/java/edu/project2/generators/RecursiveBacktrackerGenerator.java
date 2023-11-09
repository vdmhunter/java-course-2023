package edu.project2.generators;

import edu.project2.types.Cell;
import edu.project2.types.Maze;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import static edu.project2.Helper.*;

/**
 * The {@code RecursiveBacktrackerGenerator} class implements the {@link Generator} interface
 * to generate mazes using the Recursive Backtracker algorithm.
 */
public class RecursiveBacktrackerGenerator implements Generator {

    /**
     * Generates a maze of the specified height and width using the Recursive Backtracker algorithm.
     *
     * @param height the height of the maze to be generated
     * @param width  the width of the maze to be generated
     * @return a {@link Maze} object representing the generated maze
     */
    @Override
    public Maze generate(int height, int width) {
        Maze maze = createInitialMaze(height, width);
        Deque<Cell> visitingStack = initializeVisitingStack(maze);

        while (!visitingStack.isEmpty()) {
            processVisitingStack(maze, visitingStack);
        }

        return maze;
    }

    /**
     * Creates an initial maze filled with walls.
     *
     * @param height the height of the maze
     * @param width  the width of the maze
     * @return a {@link Maze} object with all cells initialized as walls
     */
    private Maze createInitialMaze(int height, int width) {
        Cell.Type initialCellType = Cell.Type.WALL;
        Cell[][] grid = createFilledMaze(height, width, initialCellType);

        return new Maze(height, width, grid);
    }

    /**
     * Initializes the stack of cells to be visited during the maze generation process.
     *
     * @param maze the maze being generated
     * @return a {@link Deque} of cells to be visited
     */
    private Deque<Cell> initializeVisitingStack(Maze maze) {
        Cell startCell = maze.grid()[0][0];
        Deque<Cell> visitingStack = new ArrayDeque<>();
        visitingStack.push(startCell);

        return visitingStack;
    }

    /**
     * Processes the stack of cells to be visited during the maze generation process.
     *
     * @param maze          the maze being generated
     * @param visitingStack the stack of cells to be visited
     */
    private void processVisitingStack(Maze maze, Deque<Cell> visitingStack) {
        Cell currentCell = visitingStack.pop();
        List<Cell> neighbors = getUnvisitedNeighbors(maze, currentCell);

        if (!neighbors.isEmpty()) {
            visitingStack.push(currentCell);

            Cell randomNeighbor = chooseRandom(neighbors);
            removeWallGrid(maze, currentCell, randomNeighbor);
            makeCellAPassage(maze, randomNeighbor);

            visitingStack.push(randomNeighbor);
        }
    }

    /**
     * Converts a cell into a passage cell in the maze.
     *
     * @param maze the maze being generated
     * @param cell the cell to be converted into a passage
     */
    private void makeCellAPassage(Maze maze, Cell cell) {
        maze.grid()[cell.getRow()][cell.getCol()].setType(Cell.Type.PASSAGE);
    }
}
