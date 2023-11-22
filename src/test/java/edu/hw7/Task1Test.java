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
    void counter_TestCounterWithConcurrency() throws InterruptedException {
        try (ExecutorService service = Executors.newFixedThreadPool(10)) {
            // Arrange
            int expected = 100;
            var counter = new Counter();

            List<Callable<Void>> tasks = IntStream.range(0, expected)
                .mapToObj(i -> (Callable<Void>) () -> {
                    counter.increment();
                    return null;
                })
                .collect(Collectors.toList());

            // Act
            service.invokeAll(tasks);
            service.shutdown();
            boolean terminated = service.awaitTermination(1, TimeUnit.MINUTES);
            int actual = counter.getCount();

            // Assert
            Assertions.assertAll(
                () -> Assertions.assertTrue(terminated),
                () -> Assertions.assertEquals(expected, actual)
            );
        }
    }
}
