package edu.hw2.task3.exceptions;

/**
 * This class represents a custom exception that is thrown when there is a problem with a connection.
 * It extends the {@link RuntimeException} class, meaning it is an unchecked exception.
 * Unchecked exceptions are not checked at compile-time, but at runtime.
 */
public class ConnectionException extends RuntimeException {
    /**
     * Constructs a new {@code ConnectionException} with a specified detail message.
     *
     * @param message the detail message.
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ConnectionException} with a specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause.
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
