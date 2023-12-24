package edu.project4.transformations;

import edu.common.Generated;
import edu.project4.geometry.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Linear} class represents a linear transformation that leaves the input point unchanged.
 */
public class Linear implements Transformation {
    /**
     * Applies the linear transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The unchanged point after applying the linear transformation.
     * @throws NullPointerException if the input point is {@code null}.
     */
    @Generated
    @Override
    public Point apply(@NotNull Point point) {
        double x = point.x();
        double y = point.y();

        return new Point(x, y);
    }
}
