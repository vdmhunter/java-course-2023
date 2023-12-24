package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Fisheye} class represents a transformation that applies a "Fisheye" effect to a 2D point.
 * It transforms the point by swapping its Cartesian coordinates and scaling them based on the distance from the origin.
 */
public class Fisheye implements Transformation {
    /**
     * Applies the "Fisheye" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Fisheye" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double re = 2 / (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) + 1);

        double newX = re * y;
        double newY = re * x;

        return new Point(newX, newY);
    }
}
