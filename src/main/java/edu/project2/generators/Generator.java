package edu.project2.generators;

import edu.project2.types.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
