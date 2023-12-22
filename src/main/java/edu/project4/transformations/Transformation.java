package edu.project4.transformations;

import edu.project4.geometry.Point;
import java.util.function.Function;

/**
 * The {@code Transformation} interface represents a mathematical transformation applied to a 2D point.
 * It extends the {@link java.util.function.Function} interface, specifying the transformation operation
 * from one {@link Point} to another.
 */
public interface Transformation extends Function<Point, Point> {
    /**
     * Applies the transformation to the given 2D point.
     *
     * @param point The input point to be transformed.
     * @return The transformed point after applying the transformation.
     */
    @Override
    Point apply(Point point);
}
