package edu.project2.solvers;

import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
