package edu.project4.models;

import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

public record Rect(
    double x,
    double y,
    double width,
    double height
) {
    public boolean contains(@NotNull Point p) {
        return p.x() >= x && p.x() < x + width && p.y() >= y && p.y() < y + height;
    }

    public @NotNull Point getRandomPoint() {
        double randomX = x + ThreadLocalRandom.current().nextDouble(0.0, width);
        double randomY = y + ThreadLocalRandom.current().nextDouble(0.0, height);

        return new Point(randomX, randomY);
    }
}
