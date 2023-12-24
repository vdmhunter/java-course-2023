package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Horseshoe} class represents a transformation that applies a "Horseshoe" effect to a 2D point.
 * It transforms the point by mapping its Cartesian coordinates to polar coordinates and applying specific
 * mathematical operations.
 */
public class Horseshoe implements Transformation {
    /**
     * Applies the "Horseshoe" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Horseshoe" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double r = Math.sqrt(x * x + y * y);

        double newX = (1 / r) * (x - y) * (x + y);
        double newY = (1 / r) * 2 * x * y;

        return new Point(newX, newY);
    }
}
