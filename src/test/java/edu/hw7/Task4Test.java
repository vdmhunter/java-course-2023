package edu.hw7;

import edu.hw7.task4.MultiThreadedPi;
import edu.hw7.task4.SingleThreadedPi;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;

/**
 * Tests for Homework 7, Task 4
 */
class Task4Test {
    @Test
    @DisplayName("Testing single-threaded Monte Carlo Pi calculation")
    void singleThreadedPi_TestCalculate() {
        // Arrange
        long totalPointsCount = 1_000_000L;

        // Act
        double pi = SingleThreadedPi.calculate(totalPointsCount);

        // Assert
        Assertions.assertTrue(Math.abs(Math.PI - pi) < 0.01D);
    }

    @Test
    @DisplayName("Testing multi thread processing Monte Carlo Pi calculation")
    void multiThreadedPi_TestCalculate() throws InterruptedException {
        // Arrange
        long totalPointsCount = 10_000_000L;
        int optimalThreadsCount = Runtime.getRuntime().availableProcessors() - 1;

        // Act
        double pi = MultiThreadedPi.calculate(totalPointsCount, optimalThreadsCount);

        // Assert
        Assertions.assertTrue(Math.abs(Math.PI - pi) < 0.001D);
    }

    @Disabled("Comparison test disabled")
    @ParameterizedTest(name = "Test {index} - Monte Carlo Pi calculation comparison for {0} points")
    @ValueSource(longs = {
        10_000_000L,
        100_000_000L,
        1_000_000_000L
    })
    @DisplayName("Monte Carlo Pi calculation comparison")
    void comparisonTest(long totalPointsCount) throws InterruptedException {
        // Single-Threaded
        long singleStartTime = System.nanoTime();
        double singlePi = SingleThreadedPi.calculate(totalPointsCount);
        long singleEndTime = System.nanoTime();

        double singleTotalExecutionTime = (singleEndTime - singleStartTime) / 1_000_000_000.0D;
        double singleDelta = Math.abs(Math.PI - singlePi);

        // Multi-Threaded
        int totalThreadsCount = Runtime.getRuntime().availableProcessors();

        for (int threadsCount : divideRange(totalThreadsCount)) {
            long multiStartTime = System.nanoTime();
            double multiPi = MultiThreadedPi.calculate(totalPointsCount, threadsCount);
            long multiEndTime = System.nanoTime();

            double multiTotalExecutionTime = (multiEndTime - multiStartTime) / 1_000_000_000.0D;
            double multiDelta = Math.abs(Math.PI - multiPi);

            printComparisonResult(
                totalPointsCount,
                threadsCount,
                singlePi,
                singleTotalExecutionTime,
                singleDelta,
                multiPi,
                multiTotalExecutionTime,
                multiDelta
            );
        }

        Assertions.assertTrue(singlePi > 3.0D && singlePi < 4.0D);
    }

    private @NotNull ArrayList<Integer> divideRange(int n) {
        if (n < 2) {
            throw new IllegalArgumentException("The input integer must be greater than 1.");
        }

        ArrayList<Integer> result = new ArrayList<>();

        result.add(2);

        if (n > 2) {
            int numberOfElements = Math.min(3, n - 1);
            double interval = (double) (n - 2) / numberOfElements;

            for (int i = 1; i < numberOfElements; i++) {
                int value = (int) Math.ceil(2 + interval * i);

                if (value < n) {
                    result.add(value);
                }
            }
        } else {
            return result;
        }

        result.add(n);

        return result;
    }

    private void printComparisonResult(
        long totalPointsCount,
        int threadsCount,
        double singlePi,
        double singleTotalExecutionTime,
        double singleDelta,
        double multiPi,
        double multiTotalExecutionTime,
        double multiDelta
    ) {
        String[] headers = {"Metric", "Single Thread", "Multi Thread", "Difference"};
        String[][] data = {
            {"*Pi*", String.valueOf(singlePi), String.valueOf(multiPi), "-"},
            {"*Delta*", String.format("%.6f", singleDelta), String.format("%.5f", multiDelta), "-"},
            {"*Execution Time*", String.valueOf(singleTotalExecutionTime), String.valueOf(multiTotalExecutionTime),
                String.format("%.5f sec.", Math.abs(multiTotalExecutionTime - singleTotalExecutionTime))},
            {"*Speedup*", "1", String.format("%.2fx", singleTotalExecutionTime / multiTotalExecutionTime), "-"}
        };

        String markdownTable = generateMarkdownTable(headers, data);
        System.out.printf("**Comparison results for %d points and %d threads**%n%n", totalPointsCount, threadsCount);
        System.out.println(markdownTable + System.lineSeparator());
    }

    private @NotNull String generateMarkdownTable(String @NotNull [] headers, String[][] data) {
        var table = new StringBuilder();

        // Header row
        for (String header : headers) {
            table.append("| ").append(header).append(" ");
        }

        table.append("|").append(System.lineSeparator());

        // Separator row
        for (String ignored : headers) {
            table.append("| --- ");
        }

        table.append("|").append(System.lineSeparator());

        // Data rows
        for (String[] row : data) {
            for (String cell : row) {
                table.append("| ").append(cell).append(" ");
            }

            table.append("|").append(System.lineSeparator());
        }

        return table.toString();
    }
}
