package edu.project2.solvers;

import edu.common.Generated;
import edu.project2.Helper;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code DfsSolver} class implements the {@link Solver} interface
 * to solve mazes using the Depth-First Search (DFS) algorithm with multiple threads.
 */
public class DfsSolver implements Solver {
    private final ExecutorService executorService;

    /**
     * Constructs a new {@code DfsSolver} with a specified number of threads.
     */
    public DfsSolver() {
        this.executorService = Executors.newFixedThreadPool(3);
    }

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
        var cameFrom = Helper.createFilledMaze(maze.height(), maze.width(), Cell.Type.UNDEFINED);
        var visited = Helper.createFilledMaze(maze.height(), maze.width(), Cell.Type.UNVISITED);

        Queue<Coordinate> cellsStack = new ArrayDeque<>();
        cellsStack.add(start);

        while (!cellsStack.isEmpty()) {
            List<Callable<Void>> tasks = new ArrayList<>();
            int numThreads = 3;

            for (int i = 0; i < numThreads; i++) {
                Coordinate currentCellIndex = cellsStack.poll();

                if (currentCellIndex != null) {
                    if (currentCellIndex.equals(end)) {
                        executorService.shutdownNow();

                        return reconstructPath(cameFrom, currentCellIndex);
                    }

                    visited[currentCellIndex.row()][currentCellIndex.col()].setType(Cell.Type.VISITED);

                    processCurrentCell(maze, currentCellIndex, visited, cameFrom, tasks, cellsStack);
                }
            }

            executeParallelTasks(tasks);
        }

        return new ArrayList<>();
    }

    /**
     * Processes the information related to the current cell in the Depth-First Search (DFS) algorithm.
     * This method updates the 'cameFrom' matrix with information about neighbors and enqueues
     * unvisited neighboring cells for further exploration.
     *
     * @param maze             The maze being solved.
     * @param currentCellIndex The coordinates of the current cell being processed.
     * @param visited          A 2D array representing the visited status of cells in the maze.
     * @param cameFrom         A 2D array representing the previous cell for each cell in the maze.
     * @param tasks            A list of tasks to be executed in parallel, adding cells to the stack.
     * @param cellsStack       The stack of cells to be explored during the DFS traversal.
     */
    private static void processCurrentCell(
        @NotNull Maze maze,
        @NotNull Coordinate currentCellIndex,
        Cell[][] visited,
        Cell[][] cameFrom,
        List<Callable<Void>> tasks,
        Queue<Coordinate> cellsStack
    ) {
        List<Cell> availableNeighbors = Helper.getAvailableNeighbors(
            maze,
            maze.grid()[currentCellIndex.row()][currentCellIndex.col()],
            visited
        );

        for (Cell neighbor : availableNeighbors) {
            cameFrom[neighbor.getRow()][neighbor.getCol()] =
                maze.grid()[currentCellIndex.row()][currentCellIndex.col()];
        }

        for (Cell neighbor : availableNeighbors) {
            tasks.add(() -> {
                cellsStack.add(new Coordinate(neighbor.getRow(), neighbor.getCol()));

                return null;
            });
        }
    }

    /**
     * Executes a list of tasks in parallel using the executor service.
     * <p>
     * This method takes a list of tasks and executes them concurrently using the
     * underlying executor service. It is designed to parallelize the processing
     * of tasks, providing potential performance improvements in scenarios where
     * parallel execution is beneficial.
     *
     * @param tasks the list of tasks to be executed in parallel
     * @implNote The method utilizes the executor service associated with the
     *     {@code DfsSolver} instance to invoke all tasks concurrently. Any interruption
     *     during the execution is caught and results in the interruption of the
     *     current thread.
     */
    @Generated
    private void executeParallelTasks(List<Callable<Void>> tasks) {
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
