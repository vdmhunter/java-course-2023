package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Cross} class represents a transformation that applies a "Cross" effect to a 2D point.
 * It scales the point based on the relationship between its x and y coordinates.
 */
public class Cross implements Transformation {
    /**
     * Applies the "Cross" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Cross" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double s = Math.sqrt(1 / (x * x - y * y));
        double newX = s * x;
        double newY = s * y;

        return new Point(newX, newY);
    }
}
