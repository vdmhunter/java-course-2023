package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Tangent} class represents a transformation that applies a "Tangent" effect to a 2D point.
 * It transforms the point by computing tangent values for its coordinates.
 */
public class Tangent implements Transformation {
    /**
     * Applies the "Tangent" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Tangent" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double newX = Math.sin(x) / Math.cos(y);
        double newY = Math.tan(y);

        return new Point(newX, newY);
    }
}
