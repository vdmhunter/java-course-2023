package edu.project4.transformations;

import edu.project4.models.Point;
import org.jetbrains.annotations.NotNull;

public class Linear implements Transformation {
    @Override
    public Point apply(@NotNull Point point) {
        return new Point(point.x(), point.y());
    }
}
