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

public class GrowingTreeGenerator implements Generator {

    @Override
    public Maze generate(int height, int width) {
        Maze maze = createInitialMaze(height, width);
        List<Cell> activeCells = initializeActiveCells(maze, height, width);

        while (!activeCells.isEmpty()) {
            processActiveCells(maze, activeCells);
        }

        return maze;
    }

    private Maze createInitialMaze(int height, int width) {
        Cell.Type initialCellType = Cell.Type.WALL;
        Cell[][] grid = createFilledMaze(height, width, initialCellType);

        return new Maze(height, width, grid);
    }

    private List<Cell> initializeActiveCells(Maze maze, int height, int width) {
        Cell initialCell = chooseRandomCell(maze, height, width);
        List<Cell> activeCells = new ArrayList<>();
        activeCells.add(initialCell);

        return activeCells;
    }

    private Cell chooseRandomCell(Maze maze, int height, int width) {
        int randomRowIndex = chooseRandomIndex(height);
        int randomColIndex = chooseRandomIndex(width);

        return maze.grid()[randomRowIndex][randomColIndex];
    }

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

    private void makeCellAPassage(Maze maze, Cell cell) {
        maze.grid()[cell.getRow()][cell.getCol()].setType(Cell.Type.PASSAGE);
    }
}
