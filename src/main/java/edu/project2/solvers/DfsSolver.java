package edu.project2.solvers;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import static edu.project2.Helper.createFilledMaze;
import static edu.project2.Helper.getAvailableNeighbors;

/**
 * The {@code DfsSolver} class implements the {@link Solver} interface
 * to solve mazes using the Depth-First Search (DFS) algorithm.
 */
public class DfsSolver implements Solver {
    /**
     * Solves a maze from a start coordinate to an end coordinate using the DFS algorithm.
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

        List<Coordinate> path = new ArrayList<>();
        boolean found = dfs(maze, start, end, visited, cameFrom, path);

        if (found) {
            return path;
        } else {
            return new ArrayList<>();
        }
    }

    private boolean dfs(
        Maze maze,
        @NotNull Coordinate current,
        Coordinate end,
        Cell[][] visited,
        Cell[][] cameFrom,
        List<Coordinate> path
    ) {
        if (current.equals(end)) {
            path.add(current);

            return true;
        }

        visited[current.row()][current.col()].setType(Cell.Type.VISITED);

        List<Cell> availableNeighbors = getAvailableNeighbors(maze, maze.grid()[current.row()][current.col()], visited);

        for (Cell neighbor : availableNeighbors) {
            cameFrom[neighbor.getRow()][neighbor.getCol()] = maze.grid()[current.row()][current.col()];
            if (dfs(maze, new Coordinate(neighbor.getRow(), neighbor.getCol()), end, visited, cameFrom, path)) {
                path.add(current);

                return true;
            }
        }

        return false;
    }
}
