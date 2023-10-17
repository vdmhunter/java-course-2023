package edu.hw2.task2;

/**
 * {@code Square} class represents a square with a specific size.
 * It extends the Rectangle class, inheriting all its methods.
 * Additionally, it provides a method {@link Square#newSquareWithSize(int)}
 * which creates a new square with a specified size.
 */
public class Square extends Rectangle {
    /**
     * Default constructor that creates a square with zero size.
     */
    public Square() {
        super(0, 0);
    }

    /**
     * This constructor creates a square by specifying the length of its sides.
     *
     * @param size the length of each side of the square.
     * @throws IllegalArgumentException if size is negative
     */
    public Square(int size) {
        super(size, size);
    }

    /**
     * Creates a new square with the specified size.
     *
     * @param size the length of each side of the new square
     * @return a new Square object with specified size
     * @throws IllegalArgumentException if size is negative
     */
    public final Square newSquareWithSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("The size of the square must be positive value.");
        }

        return new Square(size);
    }
}
