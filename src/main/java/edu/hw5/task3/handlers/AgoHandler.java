package edu.hw5.task3.handlers;

import java.time.Clock;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code AgoHandler} is a handler for parsing date strings representing a number of days ago.
 * It extends the {@link Handler} abstract class, implementing the parsing logic specific to
 * date strings containing the phrase "days ago."
 *
 * <p>The class allows customization of the clock, making it useful for testing scenarios where
 * the current date/time needs to be controlled.
 */
public class AgoHandler extends Handler {
    private final Clock clock;
    private static final Pattern PATTERN = Pattern.compile("(\\d+) days? ago");

    /**
     * Constructs an {@code AgoHandler} with the default system clock.
     */
    public AgoHandler() {
        this(Clock.systemDefaultZone());
    }

    /**
     * Constructs an {@code AgoHandler} with a specified clock.
     *
     * @param clock the clock to use for obtaining the current date and time
     */
    public AgoHandler(Clock clock) {
        this.clock = clock;
    }

    /**
     * Parses a date string representing a number of days ago and returns the corresponding {@code LocalDate}.
     * The date string is expected to match the pattern "(\\d+) days? ago."
     *
     * @param string the date string to parse
     * @return the {@code LocalDate} representing the parsed date or {@code null} if the string does not match
     * the expected pattern
     */
    @Override
    protected LocalDate parse(String string) {
        Matcher matcher = PATTERN.matcher(string);

        if (matcher.matches()) {
            int days = Integer.parseInt(matcher.group(1));

            return LocalDate.now(clock).minusDays(days);
        }

        return null;
    }
}
