package edu.project2.generators;

import edu.project2.types.Cell;
import edu.project2.types.Maze;
import java.util.ArrayList;
import java.util.List;
import static edu.project2.Helper.chooseRandom;
import static edu.project2.Helper.chooseRandomIndex;
import static edu.project2.Helper.createFilledMaze;
import static edu.project2.Helper.getUnvisitedNeighbors;
import static edu.project2.Helper.removeWallGrid;

/**
 * The {@code GrowingTreeGenerator} class implements the {@link Generator} interface
 * to generate mazes using the Growing Tree algorithm.
 */
public class GrowingTreeGenerator implements Generator {

    /**
     * Generates a maze of the specified height and width using the Growing Tree algorithm.
     *
     * @param height the height of the maze to be generated
     * @param width  the width of the maze to be generated
     * @return a {@link Maze} object representing the generated maze
     */
    @Override
    public Maze generate(int height, int width) {
        Maze maze = createInitialMaze(height, width);
        List<Cell> activeCells = initializeActiveCells(maze, height, width);

        while (!activeCells.isEmpty()) {
            processActiveCells(maze, activeCells);
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
     * Initializes the list of active cells for the maze generation process.
     *
     * @param maze   the maze being generated
     * @param height the height of the maze
     * @param width  the width of the maze
     * @return a {@link List} of active cells
     */
    private List<Cell> initializeActiveCells(Maze maze, int height, int width) {
        Cell initialCell = chooseRandomCell(maze, height, width);
        List<Cell> activeCells = new ArrayList<>();
        activeCells.add(initialCell);

        return activeCells;
    }

    /**
     * Chooses a random cell from the maze.
     *
     * @param maze   the maze being generated
     * @param height the height of the maze
     * @param width  the width of the maze
     * @return a randomly chosen {@link Cell} from the maze
     */
    private Cell chooseRandomCell(Maze maze, int height, int width) {
        int randomRowIndex = chooseRandomIndex(height);
        int randomColIndex = chooseRandomIndex(width);

        return maze.grid()[randomRowIndex][randomColIndex];
    }

    /**
     * Processes the list of active cells during the maze generation process.
     *
     * @param maze        the maze being generated
     * @param activeCells the list of active cells
     */
    private void processActiveCells(Maze maze, List<Cell> activeCells) {
        int activeCellIndex = chooseRandomIndex(activeCells.size());
        Cell activeCell = activeCells.get(activeCellIndex);

        makeCellAPassage(maze, activeCell);

        List<Cell> neighbors = getUnvisitedNeighbors(maze, activeCell);

        if (neighbors.isEmpty()) {
            activeCells.remove(activeCellIndex);
        } else {
            Cell chosenNeighbor = chooseRandom(neighbors);
            removeWallGrid(maze, activeCell, chosenNeighbor);
            activeCells.add(chosenNeighbor);
            makeCellAPassage(maze, chosenNeighbor);
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
