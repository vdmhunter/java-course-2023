package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Bubble} class represents a transformation that applies a "Bubble" effect to a 2D point.
 * It scales the point based on its distance from the origin.
 */
public class Bubble implements Transformation {
    /**
     * Applies the "Bubble" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Bubble" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        @SuppressWarnings("MagicNumber")
        double re = 4 / (Math.pow(Math.sqrt(x * x + y * y), 2) + 4);

        double newX = re * x;
        double newY = re * y;

        return new Point(newX, newY);
    }
}
