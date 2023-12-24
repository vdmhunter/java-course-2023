package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Hyperbolic} class represents a transformation that applies a "Hyperbolic" effect to a 2D point.
 * It transforms the point by mapping its Cartesian coordinates to polar coordinates and applying specific mathematical
 * operations.
 */
public class Hyperbolic implements Transformation {
    /**
     * Applies the "Hyperbolic" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Hyperbolic" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        double newX = Math.sin(theta) / r;
        double newY = r * Math.cos(theta);

        return new Point(newX, newY);
    }
}
