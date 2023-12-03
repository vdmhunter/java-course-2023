package edu.hw8;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 8, Task 2
 */
class Task2Test {
    private static final int OPTIMAL_THREADS_NUMBER = Runtime.getRuntime().availableProcessors();

    private static final ExecutorService executor = Executors.newFixedThreadPool(OPTIMAL_THREADS_NUMBER);

    @Test
    @DisplayName("Test Fibonacci calculation with concurrency")
    void fixedThreadPool_TestFibonacciCalculationWithConcurrency() throws ExecutionException, InterruptedException {
        // Arrange
        int fibonacciNumber = 100;
        BigInteger expected = new BigInteger("354224848179261915075");

        // Act
        Future<BigInteger> fib1 = executor.submit(new FibonacciTask(fibonacciNumber - 1));
        Future<BigInteger> fib2 = executor.submit(new FibonacciTask(fibonacciNumber - 2));

        BigInteger result1 = fib1.get();
        BigInteger result2 = fib2.get();

        executor.shutdown();

        BigInteger actual = result1.add(result2);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    public static class FibonacciTask implements Callable<BigInteger> {
        private final int n;

        public FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        public BigInteger call() {
            return calculateFibonacci(n);
        }
    }

    private static BigInteger calculateFibonacci(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        } else {
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
    }
}
