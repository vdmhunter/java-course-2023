package edu.project2.solvers;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import org.jetbrains.annotations.NotNull;
import static edu.project2.Helper.createFilledMaze;
import static edu.project2.Helper.getAvailableNeighbors;

/**
 * The {@code DfsMultithreadSolver} class implements the {@link Solver} interface
 * to solve mazes using the Depth-First Search (DFS) algorithm with ForkJoinPool.
 */
public class DfsMultithreadSolver implements Solver {
    /**
     * Solves a maze from a start coordinate to an end coordinate using the DFS algorithm with ForkJoinPool.
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

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            DfsTask rootTask = new DfsTask(maze, start, end, visited, cameFrom, path);

            forkJoinPool.invoke(rootTask);

            forkJoinPool.shutdown();
        }

        if (!path.isEmpty()) {
            return path;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * The {@code DfsTask} class represents a recursive task for parallelized DFS traversal.
     */
    private static class DfsTask extends RecursiveTask<Boolean> {
        private final transient Maze maze;
        private final transient Coordinate current;
        private final transient Coordinate end;
        private final transient Cell[][] visited;
        private final transient Cell[][] cameFrom;
        private final transient List<Coordinate> path;

        @Serial
        private static final long serialVersionUID = -3275857110262796134L;

        /**
         * Constructs a {@code DfsTask}.
         *
         * @param maze     the maze to be solved
         * @param current  the current coordinate
         * @param end      the ending coordinate
         * @param visited  a 2D array representing visited cells in the maze
         * @param cameFrom a 2D array representing the path from start to the current cell
         * @param path     a list representing the current path from start to end
         */
        DfsTask(
            Maze maze,
            Coordinate current,
            Coordinate end,
            Cell[][] visited,
            Cell[][] cameFrom,
            List<Coordinate> path
        ) {
            this.maze = maze;
            this.current = current;
            this.end = end;
            this.visited = visited;
            this.cameFrom = cameFrom;
            this.path = path;
        }

        /**
         * Computes the result of the task.
         *
         * @return {@code true} if the path to the destination is found, {@code false} otherwise
         */
        @Override
        protected @NotNull Boolean compute() {
            if (current.equals(end)) {
                path.add(current);

                return true;
            }

            int row = current.row();
            int col = current.col();
            visited[row][col].setType(Cell.Type.VISITED);

            List<Cell> availableNeighbors = getAvailableNeighbors(
                maze,
                maze.grid()[row][col],
                visited
            );

            List<DfsTask> tasks = new ArrayList<>();

            for (Cell neighbor : availableNeighbors) {
                cameFrom[neighbor.getRow()][neighbor.getCol()] = maze.grid()[row][col];
                DfsTask task = new DfsTask(
                    maze,
                    new Coordinate(neighbor.getRow(), neighbor.getCol()),
                    end,
                    visited,
                    cameFrom,
                    path
                );
                tasks.add(task);
            }

            invokeAll(tasks);

            for (DfsTask task : tasks) {
                if (Boolean.TRUE.equals(task.join())) {
                    path.add(current);

                    return true;
                }
            }

            return false;
        }
    }
}
