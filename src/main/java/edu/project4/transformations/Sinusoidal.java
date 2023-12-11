package edu.project4.transformations;

import edu.project4.models.Point;
import org.jetbrains.annotations.NotNull;

public class Sinusoidal implements Transformation {
    @Override
    public Point apply(@NotNull Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
