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
        Maze result = new Maze(height, width, createFilledMaze(height, width, Cell.Type.WALL));

        Cell startCell = result.getGrid()[0][0];
        Deque<Cell> visitingStack = new ArrayDeque<>();
        visitingStack.push(startCell);

        while (!visitingStack.isEmpty()) {
            Cell currentCell = visitingStack.pop();
            List<Cell> neighbors = getUnvisitedNeighbors(result, currentCell);

            if (!neighbors.isEmpty()) {
                visitingStack.push(currentCell);

                Cell randomNeighbor = chooseRandom(neighbors);
                removeWallGrid(result, currentCell, randomNeighbor);
                result.getGrid()[randomNeighbor.getRow()][randomNeighbor.getCol()].setType(Cell.Type.PASSAGE);

                visitingStack.push(randomNeighbor);
            }
        }

        return result;
    }
}
