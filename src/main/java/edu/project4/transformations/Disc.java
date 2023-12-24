package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Disc} class represents a transformation that applies a "Disc" effect to a 2D point.
 * It transforms the point by mapping its Cartesian coordinates to polar coordinates and scaling
 * the result based on the polar angle.
 */
public class Disc implements Transformation {
    /**
     * Applies the "Disc" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Disc" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        double newX = (theta / Math.PI) * Math.sin(Math.PI * r);
        double newY = (theta / Math.PI) * Math.cos(Math.PI * r);

        return new Point(newX, newY);
    }
}
