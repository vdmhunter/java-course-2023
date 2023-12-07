package edu.project2.types;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Maze} class represents a maze with a specified height and width, and a grid of cells.
 */
public record Maze(int height, int width, Cell[][] grid) {
    /**
     * Checks if the provided object is equal to this {@code Maze}.
     *
     * @param o the object to be compared for equality with this{@code Maze}
     * @return true if the provided object is equal to this {@code Maze}, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Maze maze)) {
            return false;
        }

        return height == maze.height && width == maze.width && Arrays.deepEquals(grid, maze.grid);
    }

    /**
     * Returns the hash code of this {@code Maze}.
     *
     * @return the hash code of this {@code Maze}
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(height, width);
        result = 31 * result + Arrays.deepHashCode(grid);

        return result;
    }

    /**
     * Returns a string representation of this {@code Maze}.
     *
     * @return a string representation of this {@code Maze}
     */
    @Override
    public @NotNull String toString() {
        return "Maze[height=" + height + ", width=" + width + ", grid=" + Arrays.deepToString(grid) + "]";
    }

    /**
     * Returns a list of all cells in this {@code Maze}.
     *
     * @return a list of all cells in this {@code Maze}
     */
    public List<Cell> getCells() {
        return Arrays.stream(grid)
            .flatMap(Arrays::stream)
            .toList();
    }
}
