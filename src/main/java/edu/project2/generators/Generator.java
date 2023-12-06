package edu.project2.generators;

import edu.project2.types.Maze;

/**
 * The {@code Generator} interface defines the contract for classes that generate mazes.
 */
public interface Generator {
    /**
     * Generates a maze of the specified height and width.
     *
     * @param height the height of the maze to be generated
     * @param width  the width of the maze to be generated
     * @return a {@link Maze} object representing the generated maze
     */
    Maze generate(int height, int width);
}
