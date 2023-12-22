package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Diamond} class represents a transformation that applies a "Diamond" effect to a 2D point.
 * It transforms the point by mapping its Cartesian coordinates to polar coordinates and applying sine and cosine
 * functions.
 */
public class Diamond implements Transformation {
    /**
     * Applies the "Diamond" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Diamond" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        double newX = Math.sin(theta) * Math.cos(r);
        double newY = Math.cos(theta) * Math.sin(r);

        return new Point(newX, newY);
    }
}
