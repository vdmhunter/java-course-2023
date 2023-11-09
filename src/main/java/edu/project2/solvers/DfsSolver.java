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
        var cameFrom = createFilledMaze(maze.height(), maze.width(), Cell.Type.UNDEFINED);
        var visited = createFilledMaze(maze.height(), maze.width(), Cell.Type.UNVISITED);

        Queue<Coordinate> cellsStack = new ArrayDeque<>();
        cellsStack.add(start);

        while (!cellsStack.isEmpty()) {
            Coordinate currentCellIndex = cellsStack.poll();

            if (currentCellIndex.equals(end)) {
                return reconstructPath(cameFrom, currentCellIndex);
            }

            visited[currentCellIndex.row()][currentCellIndex.col()].setType(Cell.Type.VISITED);

            List<Cell> availableNeighbors =
                getAvailableNeighbors(
                    maze,
                    maze.grid()[currentCellIndex.row()][currentCellIndex.col()],
                    visited
                );

            for (Cell neighbor : availableNeighbors) {
                cameFrom[neighbor.getRow()][neighbor.getCol()] =
                    maze.grid()[currentCellIndex.row()][currentCellIndex.col()];
            }

            for (Cell neighbor : availableNeighbors) {
                cellsStack.add(new Coordinate(neighbor.getRow(), neighbor.getCol()));
            }
        }

        return new ArrayList<>();
    }
}