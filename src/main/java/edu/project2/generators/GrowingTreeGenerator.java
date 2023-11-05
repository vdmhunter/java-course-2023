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
        Maze result = new Maze(height, width, createFilledMaze(height, width, Cell.Type.WALL));
        Cell randomCell = result.getGrid()[chooseRandomIndex(height)][chooseRandomIndex(width)];

        List<Cell> activeCells = new ArrayList<>();
        activeCells.add(randomCell);

        while (!activeCells.isEmpty()) {
            int currentCellIndex = chooseRandomIndex(activeCells.size());
            Cell currentCell = activeCells.get(currentCellIndex);
            int currentRow = currentCell.getRow();
            int currentCol = currentCell.getCol();

            result.getGrid()[currentRow][currentCol].setType(Cell.Type.PASSAGE);

            List<Cell> neighbors = getUnvisitedNeighbors(result, currentCell);

            // Remove a cell if it has no neighbors to visit
            if (neighbors.isEmpty()) {
                activeCells.remove(currentCellIndex);

                continue;
            }

            Cell randomNeighbor = chooseRandom(neighbors);

            removeWallGrid(result, currentCell, randomNeighbor);

            activeCells.add(randomNeighbor);
            result.getGrid()[randomNeighbor.getRow()][randomNeighbor.getCol()].setType(Cell.Type.PASSAGE);
        }

        return result;
    }
}
