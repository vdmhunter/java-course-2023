package edu.project2.solvers;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import static edu.project2.Helper.createFilledMaze;
import static edu.project2.Helper.getAvailableNeighbors;

public class DfsSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        var cameFrom = createFilledMaze(maze.getHeight(), maze.getWidth(), Cell.Type.UNDEFINED);
        var visited = createFilledMaze(maze.getHeight(), maze.getWidth(), Cell.Type.UNVISITED);

        Queue<Coordinate> cellsStack = new ArrayDeque<>();
        cellsStack.add(start);

        while (!cellsStack.isEmpty()) {
            Coordinate currentCellIndex = cellsStack.poll();

            // Return the complete path when we reach the goal
            if (currentCellIndex.equals(end)) {
                return reconstructPath(cameFrom, currentCellIndex);
            }

            // Mark the current cell as visited
            visited[currentCellIndex.getRow()][currentCellIndex.getCol()].setType(Cell.Type.VISITED);

            // Find all reachable, unvisited neighbors
            List<Cell> availableNeighbors =
                getAvailableNeighbors(
                    maze,
                    maze.getGrid()[currentCellIndex.getRow()][currentCellIndex.getCol()],
                    visited
                );

            // Link all the unvisited neighbors to the current node
            for (Cell neighbor : availableNeighbors) {
                cameFrom[neighbor.getRow()][neighbor.getCol()] =
                    maze.getGrid()[currentCellIndex.getRow()][currentCellIndex.getCol()];
            }

            for (Cell neighbor : availableNeighbors) {
                cellsStack.add(new Coordinate(neighbor.getRow(), neighbor.getCol()));
            }
        }

        // No solution found
        return new ArrayList<>();
    }

    private static List<Coordinate> reconstructPath(Cell[][] cameFrom, Coordinate currentIndex) {
        List<Coordinate> totalPath = new ArrayList<>();
        totalPath.add(currentIndex);

        // Backtrack through the cameFrom map to rebuild the path
        while (cameFrom[currentIndex.getRow()][currentIndex.getCol()].getType() != Cell.Type.UNDEFINED) {
            Cell cameFromCell = cameFrom[currentIndex.getRow()][currentIndex.getCol()];
            var coordinate = new Coordinate(cameFromCell.getRow(), cameFromCell.getCol());
            totalPath.add(coordinate);
        }

        return totalPath;
    }
}
