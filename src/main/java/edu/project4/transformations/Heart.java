package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Heart} class represents a transformation that applies a "Heart" effect to a 2D point.
 * It transforms the point by mapping its Cartesian coordinates to polar coordinates and applying specific
 * mathematical operations.
 */
public class Heart implements Transformation {
    /**
     * Applies the "Heart" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Heart" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        double newX = r * Math.sin(theta * r);
        double newY = -r * Math.cos(theta * r);

        return new Point(newX, newY);
    }
}
