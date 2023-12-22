package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Power} class represents a transformation that applies a "Power" effect to a 2D point.
 * It transforms the point by applying specific mathematical operations involving polar coordinates.
 */
public class Power implements Transformation {
    /**
     * Applies the "Power" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Power" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @SuppressWarnings("SpellCheckingInspection")
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double theta = Math.atan2(y, x);
        double rsth = Math.pow(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)), Math.sin(theta));

        double newX = rsth * Math.cos(theta);
        double newY = rsth * Math.sin(theta);

        return new Point(newX, newY);
    }
}
