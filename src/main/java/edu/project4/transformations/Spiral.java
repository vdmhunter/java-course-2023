package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Spiral} class represents a transformation that applies a "Spiral" effect to a 2D point.
 * It transforms the point by applying specific mathematical operations involving polar coordinates.
 */
public class Spiral implements Transformation {
    /**
     * Applies the "Spiral" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Spiral" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        double newX = (1 / r) * (Math.cos(theta) + Math.sin(r));
        double newY = (1 / r) * (Math.sin(theta) - Math.cos(r));

        return new Point(newX, newY);
    }
}
