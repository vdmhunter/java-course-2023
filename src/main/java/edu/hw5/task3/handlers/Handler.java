package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

/**
 * {@code Handler} is an abstract class representing a link in the chain of responsibility for parsing date strings.
 * Concrete implementations of this class provide specific parsing logic for different date formats.
 *
 * <p>Each handler in the chain has the option to delegate the parsing task to the next handler in the chain.
 */
public abstract class Handler {
    /**
     * The next element in the chain of responsibility.
     */
    protected Handler next;

    /**
     * Sets the next handler in the chain.
     *
     * @param handler the next handler to set in the chain
     */
    public void setNext(Handler handler) {
        next = handler;
    }

    /**
     * Handles the parsing of a date string. If the parsing is successful, an {@link Optional} containing the parsed
     * {@link LocalDate} is returned. If parsing fails, and there is a next handler in the chain, the task is delegated
     * to the next handler. If there is no next handler, an empty {@link Optional} is returned.
     *
     * @param string the date string to parse
     * @return an {@link Optional} containing the parsed {@link LocalDate} if successful,
     *     otherwise an empty {@link Optional}
     */
    public Optional<LocalDate> handle(String string) {
        try {
            return Optional.of(parse(string));
        } catch (Exception e) {
            if (next != null) {
                return next.handle(string);
            }

            return Optional.empty();
        }
    }

    protected abstract LocalDate parse(String string);
}
