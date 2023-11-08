package edu.project2.solvers;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import java.util.ArrayList;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

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
