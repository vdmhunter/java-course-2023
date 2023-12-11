package edu.hw7.task4;

import java.util.Random;

/**
 * The {@code SingleThreadedPi} class is responsible for estimating the value of Pi using a single-threaded approach.
 * It generates random points and calculates Pi based on the points within a unit circle.
 */
public final class SingleThreadedPi {
    private static final double SCALE_FACTOR = 4.0D;
    private static final Random RANDOM = new Random();

    private SingleThreadedPi() {
    }

    /**
     * Calculates an estimate of Pi using a specified number of total points in a single-threaded manner.
     *
     * @param totalPointsCount The total number of random points to be generated and used in the estimation.
     * @return The estimated value of Pi based on the generated random points.
     */
    public static double calculate(long totalPointsCount) {
        long totalPointsInsideCircleCount = 0L;

        for (long i = 0L; i < totalPointsCount; i++) {
            double x = RANDOM.nextDouble();
            double y = RANDOM.nextDouble();

            if (x * x + y * y <= 1.0D) {
                totalPointsInsideCircleCount++;
            }
        }

        return SCALE_FACTOR * totalPointsInsideCircleCount / totalPointsCount;
    }
}
