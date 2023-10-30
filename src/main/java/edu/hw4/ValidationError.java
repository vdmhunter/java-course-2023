package edu.hw4;

import java.io.Serial;

/**
 * The {@code ValidationError} class is a custom exception that extends the {@code Throwable} class. It is used
 * to represent and handle validation errors in the context of animal attributes.
 * <p/>
 * Validation errors typically occur when specific criteria or constraints are not met during the validation
 * of data related to animals. This class is designed to provide a structured way of reporting such errors by
 * encapsulating an error message.
 */
public class ValidationError extends Throwable {
    @Serial
    private static final long serialVersionUID = -7964128654217783409L;

    /**
     * Constructs a new {@code ValidationError} instance with the specified error message.
     *
     * @param message The error message that describes the validation error.
     */
    public ValidationError(String message) {
        super(message);
    }
}
