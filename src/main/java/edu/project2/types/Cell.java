package edu.project2.types;

/**
 * The {@code Cell} class represents a cell in a maze.
 */
public class Cell {

    private final int row;
    private final int col;
    private Type type;

    /**
     * The {@code Type} enum represents the possible types of a cell.
     */
    public enum Type {
        WALL, PASSAGE, VISITED, UNVISITED, UNDEFINED
    }

    /**
     * Constructs a new {@code Cell} with the specified row, column, and type.
     *
     * @param row  the row of the cell
     * @param col  the column of the cell
     * @param type the type of the cell
     */
    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    /**
     * Returns the row of the cell.
     *
     * @return the row of the cell
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of the cell.
     *
     * @return the column of the cell
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns the type of the cell.
     *
     * @return the type of the cell
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type of the cell.
     *
     * @param type the new type of the cell
     */
    public void setType(Type type) {
        this.type = type;
    }
}
