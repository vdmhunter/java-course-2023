package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * {@code DateFormatHandler} is a concrete implementation of the {@link Handler} class, specializing in parsing
 * date strings with various date formats. It uses a chain of {@link DateTimeFormatter} patterns to handle parsing.
 *
 * <p>This handler is specifically designed to parse date strings in formats like "d/M/yyyy" and "u-M-d," allowing
 * flexibility in date representations.
 */
public class DateFormatHandler extends Handler {
    private final DateTimeFormatter formatter;
    private static final int YEAR_MIN_WIDTH = 2;
    private static final int YEAR_MAX_WIDTH = 4;
    private static final int PIVOT_YEAR = 2000;

    /**
     * Constructs a {@code DateFormatHandler} with a {@link DateTimeFormatter} configured to handle
     * date strings in multiple formats, such as "d/M/yyyy" and "u-M-d."
     */
    public DateFormatHandler() {
        this.formatter = new DateTimeFormatterBuilder()
            .appendOptional(new DateTimeFormatterBuilder()
                .appendPattern("d/M/")
                .appendValueReduced(ChronoField.YEAR, YEAR_MIN_WIDTH, YEAR_MAX_WIDTH, PIVOT_YEAR)
                .toFormatter())
            .appendOptional(DateTimeFormatter.ofPattern("u-M-d"))
            .toFormatter();
    }

    /**
     * Parses a date string using the configured {@link DateTimeFormatter}.
     *
     * @param string the date string to parse
     * @return the parsed {@link LocalDate}
     * @see DateTimeFormatter
     */
    @Override
    protected LocalDate parse(String string) {
        return LocalDate.parse(string, formatter);
    }
}
