package edu.hw5.task3;

import edu.hw5.task3.handlers.Handler;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * {@code DateParser} is a utility class responsible for parsing date strings using a chain of responsibility pattern.
 * It delegates the parsing task to a list of {@link Handler} instances, each specializing in a specific date format.
 *
 * <p>The order of handlers in the list determines the sequence in which they attempt to parse the date string.
 * If a handler in the chain successfully parses the date string, the parsing process stops, and the result is returned.
 * If none of the handlers in the chain can parse the date string, an empty {@link Optional} is returned.
 */
public final class DateParser {
    private final List<Handler> handlers;

    /**
     * Constructs a {@code DateParser} with the specified list of handlers.
     *
     * @param handlers the list of handlers in the chain of responsibility
     */
    public DateParser(List<Handler> handlers) {
        this.handlers = handlers;
    }

    /**
     * Parses a date string using the chain of responsibility pattern.
     *
     * <p>The method delegates the parsing task to the first handler in the list. If the handler successfully parses
     * the date string, the result is returned as an {@link Optional} containing the parsed {@link LocalDate}.
     * If none of the handlers can parse the date string, an empty {@link Optional} is returned.
     *
     * @param string the date string to parse
     * @return an {@link Optional} containing the parsed {@link LocalDate} if successful,
     * otherwise an empty {@link Optional}
     */
    public Optional<LocalDate> parseDate(String string) {
        return handlers.getFirst().handle(string);
    }
}
