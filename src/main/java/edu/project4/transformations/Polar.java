package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Polar} class represents a transformation that converts Cartesian coordinates to polar coordinates.
 */
public class Polar implements Transformation {
    /**
     * Applies the polar transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after converting Cartesian coordinates to polar coordinates.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        double newX = theta / Math.PI;
        double newY = r - 1;

        return new Point(newX, newY);
    }
}
