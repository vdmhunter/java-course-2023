package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code ComputerClubAnalytics} class provides methods for analyzing and calculating
 * average session durations based on time data in a specified format.
 * The class is designed to work with a list of string representations of time intervals.
 * Time intervals must follow the pattern "yyyy-MM-dd, HH:mm - yyyy-MM-dd, HH:mm".
 * The class includes a static method {@link ComputerClubAnalytics#getAverageTime(List)}
 * to calculate the average session duration.
 */
public final class ComputerClubAnalytics {
    private static final Pattern PATTERN;
    private static final DateTimeFormatter FORMATTER;
    private static final String REGEX = "^(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - "
        + "(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})$";
    private static final int MINUTES_PER_HOUR = 60;

    private ComputerClubAnalytics() {
    }

    static {
        PATTERN = Pattern.compile(REGEX);
        FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    }

    /**
     * Calculates and returns the average session duration based on a list of time intervals.
     *
     * @param lines A list of string representations of time intervals.
     * @return A formatted string representing the average session duration in hours and minutes.
     * @throws IllegalArgumentException if the input list is empty.
     * @throws NullPointerException     if the input list is null.
     */
    public static String getAverageTime(List<String> lines) {
        Objects.requireNonNull(lines);

        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Input list 'lines' must be not empty");
        }

        Duration totalDuration = lines.stream()
            .map(PATTERN::matcher)
            .filter(Matcher::find)
            .map(matcher -> {
                try {
                    LocalDateTime startDateTime = LocalDateTime.parse(matcher.group(1), FORMATTER);
                    LocalDateTime endDateTime = LocalDateTime.parse(matcher.group(2), FORMATTER);

                    return Duration.between(startDateTime, endDateTime);
                } catch (java.time.format.DateTimeParseException e) {
                    return Duration.ZERO;
                }
            })
            .reduce(Duration.ZERO, Duration::plus);

        Duration averageSessionDuration = totalDuration.dividedBy(lines.size());
        long averageHours = averageSessionDuration.toHours();
        long averageMinutes = averageSessionDuration.toMinutes() % MINUTES_PER_HOUR;

        return String.format("%1$dh %2$dm", averageHours, averageMinutes);
    }
}
