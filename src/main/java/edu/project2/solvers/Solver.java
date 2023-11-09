package edu.project2.solvers;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Solver} interface defines the contract for classes that solve mazes.
 */
public interface Solver {
    /**
     * Solves a maze from a start coordinate to an end coordinate.
     *
     * @param maze  the maze to be solved
     * @param start the starting coordinate
     * @param end   the ending coordinate
     * @return a {@link List} of {@link Coordinate}s representing the path from start to end
     */
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

    /**
     * Reconstructs the path from a start coordinate to an end coordinate using a map of cells.
     *
     * @param cameFrom     a 2D array of Cells representing the map of cells
     * @param currentIndex the current coordinate
     * @return a {@link List} of {@link Coordinate}s representing the path from start to end
     */
    default List<Coordinate> reconstructPath(Cell[][] cameFrom, Coordinate currentIndex) {
        List<Coordinate> totalPath = new ArrayList<>();
        totalPath.add(currentIndex);

        var currIdx = currentIndex;

        // Backtrack through the cameFrom map to rebuild the path
        while (cameFrom[currIdx.row()][currIdx.col()].getType() != Cell.Type.UNDEFINED) {
            Cell cameFromCell = cameFrom[currIdx.row()][currIdx.col()];
            currIdx = new Coordinate(cameFromCell.getRow(), cameFromCell.getCol());
            totalPath.add(currIdx);
        }

        return totalPath;
    }
}
