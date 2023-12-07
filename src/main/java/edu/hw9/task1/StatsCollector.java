package edu.hw9.task1;

import edu.common.Generated;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for collecting and aggregating statistical data for different metrics.
 */
public class StatsCollector {
    private final Map<String, List<Double>> data = new ConcurrentHashMap<>();
    private static final long TIMEOUT_SECONDS = 10L;
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Adds new metric values to the collector.
     *
     * @param metricName The name of the metric.
     * @param values     An array of double values representing the metric readings.
     */
    public void push(String metricName, double[] values) {
        data.compute(metricName, (key, existingValues) -> {
            var ev = existingValues;

            if (ev == null) {
                ev = new CopyOnWriteArrayList<>();
            }

            for (double value : values) {
                ev.add(value);
            }

            return ev;
        });
    }

    /**
     * Calculates and returns statistical results for each metric in the collector.
     *
     * @return A list of {@link StatResult} objects containing statistical information for each metric.
     */
    public List<StatResult> stats() {
        List<StatResult> results = new ArrayList<>();

        try (ExecutorService executorService = Executors.newFixedThreadPool(data.size())) {

            for (Map.Entry<String, List<Double>> entry : data.entrySet()) {
                String metricName = entry.getKey();
                List<Double> values = entry.getValue();

                executorService.submit(() -> {
                    double sum = values.stream().mapToDouble(Double::doubleValue).sum();
                    double average = values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0D);
                    double max = values.stream().mapToDouble(Double::doubleValue).max().orElse(0.0D);
                    double min = values.stream().mapToDouble(Double::doubleValue).min().orElse(0.0D);

                    StatResult result = new StatResult(metricName, sum, average, max, min);
                    results.add(result);
                });
            }

            executorService.shutdown();

            handleExecutorServiceTermination(executorService);
        }

        return results;
    }

    /**
     * Handles the termination of the provided {@link ExecutorService}, logging a warning if termination takes longer
     * than expected.
     *
     * @param executorService The ExecutorService to be terminated.
     */
    @Generated
    private static void handleExecutorServiceTermination(@NotNull ExecutorService executorService) {
        try {
            boolean terminated = executorService.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);

            if (!terminated) {
                LOGGER.warn("ExecutorService did not terminate within the specified time.");
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
