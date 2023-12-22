package edu.project4.geometry;

import java.util.Objects;
import edu.common.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a pixel with color and hit count information.
 */
public final class Pixel {
    private int red;
    private int green;
    private int blue;
    private int alpha;
    private int hitCount;
    private double normal;

    /**
     * Constructs a {@code Pixel} with the specified color components and hit count.
     *
     * @param red      The red component of the pixel color.
     * @param green    The green component of the pixel color.
     * @param blue     The blue component of the pixel color.
     * @param alpha    The alpha component (transparency) of the pixel color.
     * @param hitCount The hit count representing the pixel's occurrence in a rendering process.
     */
    public Pixel(int red, int green, int blue, int alpha, int hitCount) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        this.hitCount = hitCount;
        this.normal = 0.0D;
    }

    /**
     * Gets the red component of the pixel color.
     *
     * @return The red component.
     */
    public int getRed() {
        return red;
    }

    /**
     * Sets the red component of the pixel color.
     *
     * @param red The new red component value.
     */
    public void setRed(int red) {
        this.red = red;
    }

    /**
     * Gets the green component of the pixel color.
     *
     * @return The green component.
     */
    public int getGreen() {
        return green;
    }

    /**
     * Sets the green component of the pixel color.
     *
     * @param green The new green component value.
     */
    public void setGreen(int green) {
        this.green = green;
    }

    /**
     * Gets the blue component of the pixel color.
     *
     * @return The blue component.
     */
    public int getBlue() {
        return blue;
    }

    /**
     * Sets the blue component of the pixel color.
     *
     * @param blue The new blue component value.
     */
    public void setBlue(int blue) {
        this.blue = blue;
    }

    /**
     * Gets the alpha component (transparency) of the pixel color.
     *
     * @return The alpha component.
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     * Sets the alpha component (transparency) of the pixel color.
     *
     * @param alpha The new alpha component value.
     */
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    /**
     * Gets the hit count representing the pixel's occurrence in a rendering process.
     *
     * @return The hit count.
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * Gets the normal value associated with the pixel.
     *
     * @return The normal value.
     */
    public double getNormal() {
        return normal;
    }

    /**
     * Sets the normal value associated with the pixel.
     *
     * @param normal The new normal value.
     */
    public void setNormal(double normal) {
        this.normal = normal;
    }

    /**
     * Increments the hit count of the pixel.
     */
    public void incHitCount() {
        this.hitCount++;
    }

    /**
     * Checks if this pixel is equal to another object.
     *
     * @param obj The object to compare with.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Generated
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        var that = (Pixel) obj;

        return this.red == that.red
            && this.green == that.green
            && this.blue == that.blue
            && this.alpha == that.alpha
            && this.hitCount == that.hitCount;
    }

    /**
     * Computes the hash code for this {@code Pixel}.
     *
     * @return The hash code value.
     */
    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue, alpha, hitCount);
    }

    /**
     * Returns a string representation of this {@code Pixel}.
     *
     * @return A string representation of the object.
     */
    @Contract(pure = true)
    @Generated
    @Override
    public @NotNull String toString() {
        return "Pixel[r=" + red + ", g=" + green + ", b=" + blue + ", alpha=" + alpha + ", hitCount=" + hitCount + "]";
    }
}
