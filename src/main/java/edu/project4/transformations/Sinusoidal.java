package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Sinusoidal} class represents a transformation that applies a sinusoidal effect to each coordinate
 * of a 2D point.
 */
public class Sinusoidal implements Transformation {
    /**
     * Applies the sinusoidal transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the sinusoidal effect to each coordinate.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        return new Point(Math.sin(x), Math.sin(y));
    }
}
