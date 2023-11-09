package edu.project2.types;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record Maze(int height, int width, Cell[][] grid) {
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

    @Override
    public int hashCode() {
        int result = Objects.hash(height, width);
        result = 31 * result + Arrays.deepHashCode(grid);

        return result;
    }

    @Override
    public String toString() {
        return "Maze[height=" + height + ", width=" + width + ", grid=" + Arrays.deepToString(grid) + "]";
    }

    public List<Cell> getCells() {
        return Arrays.stream(grid)
            .flatMap(Arrays::stream)
            .toList();
    }
}
