package edu.project2.generators;

import edu.project2.types.Cell;
import edu.project2.types.Maze;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import static edu.project2.Helper.chooseRandom;
import static edu.project2.Helper.createFilledMaze;
import static edu.project2.Helper.getUnvisitedNeighbors;
import static edu.project2.Helper.removeWallGrid;

public class RecursiveBacktrackerGenerator implements Generator {

    @Override
    public Maze generate(int height, int width) {
        Maze maze = createInitialMaze(height, width);
        Deque<Cell> visitingStack = initializeVisitingStack(maze);

        while (!visitingStack.isEmpty()) {
            processVisitingStack(maze, visitingStack);
        }

        return maze;
    }

    private Maze createInitialMaze(int height, int width) {
        Cell.Type initialCellType = Cell.Type.WALL;
        Cell[][] grid = createFilledMaze(height, width, initialCellType);

        return new Maze(height, width, grid);
    }

    private Deque<Cell> initializeVisitingStack(Maze maze) {
        Cell startCell = maze.grid()[0][0];
        Deque<Cell> visitingStack = new ArrayDeque<>();
        visitingStack.push(startCell);

        return visitingStack;
    }

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

    private void makeCellAPassage(Maze maze, Cell cell) {
        maze.grid()[cell.getRow()][cell.getCol()].setType(Cell.Type.PASSAGE);
    }
}
