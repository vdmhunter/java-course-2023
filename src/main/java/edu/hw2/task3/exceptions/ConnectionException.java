package edu.hw2.task3.exceptions;

import java.io.Serial;

/**
 * This class represents a custom exception that is thrown when there is a problem with a connection.
 * It extends the {@link RuntimeException} class, meaning it is an unchecked exception.
 * Unchecked exceptions are not checked at compile-time, but at runtime.
 */
public class ConnectionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7862215556748299054L;

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
