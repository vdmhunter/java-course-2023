package edu.project2.solvers;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.jetbrains.annotations.NotNull;
import static edu.project2.Helper.createFilledMaze;
import static edu.project2.Helper.getAvailableNeighbors;

/**
 * The {@code BfsSolver} class implements the {@link Solver} interface to solve mazes
 * using the Breadth-First Search (BFS) algorithm.
 */
public class BfsSolver implements Solver {
    /**
     * Solves a maze from a start coordinate to an end coordinate using the BFS algorithm.
     *
     * @param maze  the maze to be solved
     * @param start the starting coordinate
     * @param end   the ending coordinate
     * @return a {@link List} of {@link Coordinate}s representing the path from start to end
     */
    @Override
    public List<Coordinate> solve(@NotNull Maze maze, Coordinate start, Coordinate end) {
        var cameFrom = createFilledMaze(maze.height(), maze.width(), Cell.Type.UNDEFINED);
        var visited = createFilledMaze(maze.height(), maze.width(), Cell.Type.UNVISITED);

        Queue<Coordinate> cellsQueue = new LinkedList<>();
        cellsQueue.add(start);

        while (!cellsQueue.isEmpty()) {
            Coordinate currentCellIndex = cellsQueue.poll();

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

                visited[neighbor.getRow()][neighbor.getCol()].setType(Cell.Type.VISITED);
                cellsQueue.add(new Coordinate(neighbor.getRow(), neighbor.getCol()));
            }
        }

        return new ArrayList<>();
    }
}
