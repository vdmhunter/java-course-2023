package edu.project2.types;

import java.util.Objects;

public class Coordinate {
    private final int row;
    private final int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Coordinate coordinate = (Coordinate) o;

        return row == coordinate.row && col == coordinate.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

