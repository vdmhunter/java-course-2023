package edu.hw7;

import edu.hw7.task1.Counter;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 7, Task 1
 */
class Task1Test {
    @Test
    @DisplayName("Increment counter with multiple threads")
    void counter_TestCounterWithConcurrency() {
        // Arrange
        var counter = new Counter();
        int optimalThreadCount = Runtime.getRuntime().availableProcessors() - 1;
        int operationsPerThread = 10_000;
        int expected = optimalThreadCount * operationsPerThread;

        List<Callable<Void>> tasks = IntStream.range(0, optimalThreadCount)
            .mapToObj(i -> (Callable<Void>) () -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    counter.increment();
                }
                return null;
            })
            .collect(Collectors.toList());

        try (ExecutorService service = Executors.newFixedThreadPool(optimalThreadCount)) {
            // Act
            service.invokeAll(tasks);
            service.shutdown();
            boolean terminated = service.awaitTermination(10, TimeUnit.SECONDS);
            int actual = counter.getCount();

            // Assert
            Assertions.assertAll(
                () -> Assertions.assertTrue(terminated),
                () -> Assertions.assertEquals(expected, actual)
            );
        } catch (InterruptedException ignored) {
        }
    }
}
