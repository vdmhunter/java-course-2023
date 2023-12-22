package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Swirl} class represents a transformation that applies a "Swirl" effect to a 2D point.
 * It transforms the point by swirling its coordinates based on specific mathematical operations.
 */
public class Swirl implements Transformation {
    /**
     * Applies the "Swirl" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Swirl" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double r = Math.sqrt(x * x + y * y);

        double newX = (x * Math.sin(Math.pow(r, 2))) - (y * Math.cos(Math.pow(r, 2)));
        double newY = (x * Math.cos(Math.pow(r, 2))) + (y * Math.sin(Math.pow(r, 2)));

        return new Point(newX, newY);
    }
}
