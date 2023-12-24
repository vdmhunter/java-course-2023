package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Spherical} class represents a transformation that applies a "Spherical" effect to a 2D point.
 * It transforms the point by mapping its Cartesian coordinates to spherical coordinates.
 */
public class Spherical implements Transformation {
    /**
     * Applies the "Spherical" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after mapping Cartesian coordinates to spherical coordinates.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double r = Math.sqrt(x * x + y * y);

        return new Point(x / (r * r), y / (r * r));
    }
}
