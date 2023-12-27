package edu.hw7.task4;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The {@code MultiThreadedPi} class is responsible for estimating the value of Pi using a multi thread
 * processing approach.
 * It utilizes multiple threads to distribute the workload and improve performance in estimating Pi
 * based on random points.
 */
public final class MultiThreadedPi {
    private static final double SCALE_FACTOR = 4.0D;

    private MultiThreadedPi() {
    }

    /**
     * Calculates an estimate of Pi using a specified number of total points and a given number of threads.
     *
     * @param totalPointsCount The total number of random points to be generated and used in the estimation.
     * @param threadsCount     The number of threads to be used for parallel processing.
     * @return The estimated value of Pi based on the generated random points.
     * @throws InterruptedException If any of the threads is interrupted while waiting for completion.
     */
    public static double calculate(long totalPointsCount, int threadsCount) throws InterruptedException {
        long pointsPerThread = totalPointsCount / threadsCount;

        Thread[] threads = new Thread[threadsCount];
        PiEstimator[] estimators = new PiEstimator[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            estimators[i] = new PiEstimator(pointsPerThread);
            threads[i] = new Thread(estimators[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int totalPointsInsideCircleCount = Arrays.stream(estimators)
            .mapToInt(PiEstimator::getPointsInsideCircleCount)
            .sum();

        return SCALE_FACTOR * totalPointsInsideCircleCount / totalPointsCount;
    }

    /**
     * The {@code PiEstimator} class is a Runnable implementation used for estimating Pi within a specified range
     * of random points.
     */
    static class PiEstimator implements Runnable {
        private final long pointsCount;
        private int pointsInsideCircleCount = 0;

        /**
         * Constructs a {@code PiEstimator} with the given number of points.
         *
         * @param pointsCount The total number of random points to be generated and used in the estimation.
         */
        PiEstimator(long pointsCount) {
            this.pointsCount = pointsCount;
        }

        /**
         * Gets the count of points inside the circle.
         *
         * @return The count of points inside the circle, used in the Pi estimation.
         */
        public int getPointsInsideCircleCount() {
            return pointsInsideCircleCount;
        }

        /**
         * Runs the Pi estimation process, generating random points and counting those inside the circle.
         */
        @Override
        public void run() {
            for (long i = 0L; i < pointsCount; i++) {
                double x = ThreadLocalRandom.current().nextDouble();
                double y = ThreadLocalRandom.current().nextDouble();

                if (x * x + y * y <= 1.0D) {
                    pointsInsideCircleCount++;
                }
            }
        }
    }
}
