package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Cylinder} class represents a transformation that applies a "Cylinder" effect to a 2D point.
 * It transforms the point by mapping the x-coordinate to the sine of the x-coordinate and retaining the y-coordinate.
 */
public class Cylinder implements Transformation {
    /**
     * Applies the "Cylinder" transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the "Cylinder" effect.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        double newX = Math.sin(x);

        return new Point(newX, y);
    }
}
