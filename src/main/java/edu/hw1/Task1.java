package edu.hw1;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code Task1} class provides a method {@link Task1#minutesToSeconds(String)}
 * which returns the total length of the video in seconds.
 */
public final class Task1 {
    private static final Pattern PATTERN;
    private static final int SECONDS_PER_MINUTE = 60;

    private Task1() {
    }

    static {
        PATTERN = Pattern.compile("^(?<minutes>\\d{1,5}):(?<seconds>0?\\d|[1-5]\\d)$");
    }

    /**
     * Converts a time string in the format "mm:ss" to the total number of seconds.
     *
     * @param str A non-null string representing the time in the "mm:ss" format,
     *            where "mm" is an integer between 0 and 99999 representing minutes,
     *            and "ss" is an integer between 0 and 59 representing seconds.
     * @return An integer representing the total length of the video in seconds.
     *     If the input string is not in the correct format, returns -1.
     * @throws NullPointerException If the input string is {@code null}.
     */
    public static int minutesToSeconds(String str) {
        Objects.requireNonNull(str);

        Matcher matcher = PATTERN.matcher(str);

        if (matcher.matches()) {
            String minutesGroup = matcher.group("minutes");
            String secondsGroup = matcher.group("seconds");

            int minutes = Integer.parseInt(minutesGroup);
            int seconds = Integer.parseInt(secondsGroup);

            return minutes * SECONDS_PER_MINUTE + seconds;
        } else {
            return -1;
        }
    }
}
