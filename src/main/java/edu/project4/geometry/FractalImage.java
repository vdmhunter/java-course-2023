package edu.project4.geometry;

import edu.common.Generated;
import java.util.Arrays;
import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a fractal image composed of pixels.
 */
public record FractalImage(Pixel[] data, int width, int height) {
    /**
     * Creates a new {@code FractalImage} with the specified width and height, initializing all pixels to black.
     *
     * @param width  The width of the image.
     * @param height The height of the image.
     * @return A new FractalImage instance.
     */
    @Contract("_, _ -> new")
    public static @NotNull FractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];

        for (int i = 0; i < data.length; ++i) {
            data[i] = new Pixel(0, 0, 0, 0, 0);
        }

        return new FractalImage(data, width, height);
    }

    /**
     * Checks if the given coordinates are within the bounds of the image.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return {@code true} if the coordinates are within the image bounds; {@code false} otherwise.
     */
    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Gets the pixel at the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The {@code Pixel} at the specified coordinates.
     */
    public Pixel getPixel(int x, int y) {
        return data[y * width + x];
    }

    /**
     * Checks if this {@code FractalImage} is equal to another object.
     *
     * @param obj The object to compare with.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Generated
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FractalImage other)) {
            return false;
        }

        return width == other.width && height == other.height && Arrays.equals(data, other.data);
    }

    /**
     * Computes the hash code for this {@code FractalImage}.
     *
     * @return The hash code value.
     */
    @Generated
    @Override
    public int hashCode() {
        int result = Objects.hash(width, height);
        result = 31 * result + Arrays.hashCode(data);

        return result;
    }

    /**
     * Returns a string representation of this {@code FractalImage}.
     *
     * @return A string representation of the object.
     */
    @Generated
    @Override
    public @NotNull String toString() {
        return "FractalImage[width=" + width + ", height=" + height + ", data=" + Arrays.toString(data) + "]";
    }
}
