package edu.hw9;

import edu.hw9.task1.StatResult;
import edu.hw9.task1.StatsCollector;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 9, Task 1
 */
class Task1Test {
    @Test
    @DisplayName("StatsCollector Tests - Metric Aggregation")
    void statsCollector_TestMetrics() {
        // Arrange
        StatsCollector collector = new StatsCollector();

        collector.push("metric1", new double[] {0.1D, 0.2D, 1.4D, 5.1D, 0.3D});
        collector.push("metric2", new double[] {2.1D, 3.5D, 0.8D, 1.2D, 4.6D});
        collector.push("metric3", new double[] {5.4D, 1.2D, 1.7D, 2.9D, 0.5D});
        collector.push("metric1", new double[] {6.9D, 1.4D, 2.7D, 0.6D, 3.4D});
        collector.push("metric2", new double[] {1.3D, 2.8D, 9.1D, 4.0D, 2.7D});

        // Act
        List<StatResult> results = collector.stats();

        StatResult resultForMetric1 = results.stream()
            .filter(statResult -> statResult.metricName().equals("metric1"))
            .findFirst()
            .orElse(null);
        StatResult resultForMetric2 = results.stream()
            .filter(statResult -> statResult.metricName().equals("metric2"))
            .findFirst()
            .orElse(null);
        StatResult resultForMetric3 = results.stream()
            .filter(statResult -> statResult.metricName().equals("metric3"))
            .findFirst()
            .orElse(null);

        // Assert
        Assertions.assertAll(
            () -> {
                Assertions.assertNotNull(resultForMetric1);
                Assertions.assertEquals(22.1D, resultForMetric1.sum());
                Assertions.assertEquals(2.21D, resultForMetric1.average());
                Assertions.assertEquals(6.9D, resultForMetric1.max());
                Assertions.assertEquals(0.1D, resultForMetric1.min());
            },
            () -> {
                Assertions.assertNotNull(resultForMetric2);
                Assertions.assertEquals(32.1D, resultForMetric2.sum());
                Assertions.assertEquals(3.21D, resultForMetric2.average());
                Assertions.assertEquals(9.1D, resultForMetric2.max());
                Assertions.assertEquals(0.8D, resultForMetric2.min());
            },
            () -> {
                Assertions.assertNotNull(resultForMetric3);
                Assertions.assertEquals(11.7D, resultForMetric3.sum());
                Assertions.assertEquals(2.34D, resultForMetric3.average());
                Assertions.assertEquals(5.4D, resultForMetric3.max());
                Assertions.assertEquals(0.5D, resultForMetric3.min());
            }
        );
    }
}
