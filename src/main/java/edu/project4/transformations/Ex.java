package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Ex} class represents a transformation that applies an "Ex" effect to a 2D point.
 * It transforms the point by mapping its Cartesian coordinates to polar coordinates and applying
 * specific mathematical operations.
 */
public class Ex implements Transformation {
    private static final int THREE = 3;

    /**
     * Applies the "Ex" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Ex" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        double p0 = Math.sin(theta + r);
        double p1 = Math.cos(theta - r);

        double newX = r * (Math.pow(p0, THREE) + Math.pow(p1, THREE));
        double newY = r * (Math.pow(p0, THREE) - Math.pow(p1, THREE));

        return new Point(newX, newY);
    }
}
