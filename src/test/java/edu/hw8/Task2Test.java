package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 8, Task 2
 */
class Task2Test {
    private static final int OPTIMAL_THREADS_NUMBER = Runtime.getRuntime().availableProcessors() - 1;
    private static final ThreadPool threadPool = FixedThreadPool.create(OPTIMAL_THREADS_NUMBER);
    private static final ConcurrentHashMap<Integer, BigInteger> fibCache = new ConcurrentHashMap<>();
    private static final BigInteger VERY_LARGE_FIBONACCI = new BigInteger("43466557686937456435688527675040625" +
        "802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322" +
        "471161642996440906533187938298969649928516003704476137795166849228875");
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Test Fibonacci calculation with single thread")
    void fixedThreadPool_TestFibonacciCalculationWithSingleThread() {
        // Arrange
        int n = 1_000;

        // Act
        long startTime = System.nanoTime();
        BigInteger actual = calculateFibonacciSingleThread(n);
        LOGGER.trace("Execution Time with single thread: " + (System.nanoTime() - startTime) + " nanoseconds");

        // Assert
        Assertions.assertEquals(VERY_LARGE_FIBONACCI, actual);
    }

    @Test
    @DisplayName("Test Fibonacci calculation with multi thread")
    void fixedThreadPool_TestFibonacciCalculationWithMultiThread() throws Exception {
        // Arrange
        int n = 1_000;
        var latch = new CountDownLatch(n);

        for (int i = 1; i <= n; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                calculateFibonacciMultiThread(finalI);
                latch.countDown();
            });
        }

        // Act
        long startTime = System.nanoTime();
        threadPool.start();
        latch.await();
        BigInteger actual = fibCache.get(n);
        threadPool.close();

        LOGGER.trace("Execution Time with multi thread: " + (System.nanoTime() - startTime) + " nanoseconds");

        // Assert
        Assertions.assertEquals(VERY_LARGE_FIBONACCI, actual);
    }

    private static BigInteger calculateFibonacciSingleThread(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        }

        if (n == 1) {
            return BigInteger.ONE;
        }

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        BigInteger c = BigInteger.ZERO;

        for (int i = 2; i <= n; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }

        return c;
    }

    private static void calculateFibonacciMultiThread(int n) {
        if (n == 0) {
            fibCache.put(0, BigInteger.ZERO);

            return;
        }

        if (n == 1) {
            fibCache.put(1, BigInteger.ONE);

            return;
        }

        fibCache.computeIfAbsent(n, k -> {
            if (fibCache.containsKey(k - 1) && fibCache.containsKey(k - 2)) {
                BigInteger a = fibCache.get(k - 2);
                BigInteger b = fibCache.get(k - 1);

                return a.add(b);
            } else {
                BigInteger a = BigInteger.ZERO;
                BigInteger b = BigInteger.ONE;
                BigInteger c = BigInteger.ZERO;

                for (int i = 2; i <= k; i++) {
                    c = a.add(b);
                    a = b;
                    b = c;
                }

                return c;
            }
        });
    }
}
