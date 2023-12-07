package edu.hw9.task1;

/**
 * Represents the statistical result for a metric, including the metric name, sum, average, max, and min values.
 */
public record StatResult(String metricName, double sum, double average, double max, double min) {
}
