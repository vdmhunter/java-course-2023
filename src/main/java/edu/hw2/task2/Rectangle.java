package edu.hw2.task2;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Rectangle} class represents a rectangle with a specific width and height.
 * <br/>
 * It provides methods:
 * <ul>
 * <li>{@link Rectangle#newRectangleWithWidth(int)}</li>
 * <li>{@link Rectangle#newRectangleWithWidth(int)}</li>
 * <li>{@link Rectangle#area()}</li>
 * </ul>
 * to create new rectangles with the same height or width, and to calculate the area of the rectangle.
 */
public class Rectangle {
    private final int width;

    private final int height;

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public final int getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public final int getHeight() {
        return height;
    }

    /**
     * Default constructor that creates a rectangle with zero width and zero height.
     */
    public Rectangle() {
        this(0, 0);
    }

    /**
     * Constructor that creates a rectangle with specified width and height.
     *
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     * @throws IllegalArgumentException if either width or height is negative
     */
    public Rectangle(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("The width and height of the rectangle must be positive values.");
        }

        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new rectangle with the specified width and same height as this rectangle.
     *
     * @param width the width of the new rectangle
     * @return a new Rectangle object with specified width and same height as this rectangle
     * @throws IllegalArgumentException if width is negative
     */
    @Contract("_ -> new")
    public final @NotNull Rectangle newRectangleWithWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("The width of the rectangle must be positive value.");
        }

        return new Rectangle(width, this.height);
    }

    /**
     * Creates a new rectangle with the specified height and same width as this rectangle.
     *
     * @param height the height of the new rectangle
     * @return a new Rectangle object with specified height and same width as this rectangle
     * @throws IllegalArgumentException if height is negative
     */
    public final Rectangle newRectangleWithHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("The height of the rectangle must be positive value.");
        }

        return new Rectangle(this.width, height);
    }

    /**
     * Calculates and returns the area of this rectangle.
     *
     * @return the area of this rectangle
     */
    public int area() {
        return width * height;
    }
}
