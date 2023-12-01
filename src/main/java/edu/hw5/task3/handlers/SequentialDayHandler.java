package edu.hw5.task3.handlers;

import java.time.Clock;
import java.time.LocalDate;

/**
 * {@code SequentialDayHandler} is a concrete implementation of the {@link Handler} class,
 * specializing in parsing date strings representing sequential days, such as "today," "yesterday," or "tomorrow."
 *
 * <p>This handler provides the ability to parse date strings representing the current day, the previous day,
 * or the next day.
 * It uses a {@link Clock} for flexibility in obtaining the current date, allowing controlled clock settings
 * for testing scenarios.
 */
public class SequentialDayHandler extends Handler {
    private final Clock clock;

    /**
     * Constructs a {@code SequentialDayHandler} with the default system clock.
     */
    public SequentialDayHandler() {
        this(Clock.systemDefaultZone());
    }

    /**
     * Constructs a {@code SequentialDayHandler} with a specified clock.
     *
     * @param clock the clock to use for obtaining the current date
     */
    public SequentialDayHandler(Clock clock) {
        this.clock = clock;
    }

    /**
     * Parses a date string representing a sequential day ("today," "yesterday," or "tomorrow") and returns
     * the corresponding {@link LocalDate}. The parsing is case-insensitive.
     *
     * @param string the date string to parse
     * @return the parsed {@link LocalDate} representing the current, previous, or next day, or {@code null}
     *     if the string does not match any recognized sequential day
     * @see LocalDate
     * @see Clock
     */
    @Override
    protected LocalDate parse(String string) {
        return switch (string.trim().toLowerCase()) {
            case "today" -> LocalDate.now(clock).plusDays(0);
            case "yesterday" -> LocalDate.now(clock).plusDays(-1);
            case "tomorrow" -> LocalDate.now(clock).plusDays(1);
            default -> null;
        };
    }
}
