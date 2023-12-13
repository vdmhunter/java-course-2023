package edu.hw8.task3;

import edu.common.Generated;

/**
 * The {@code Md5HashingException} class is a custom exception that extends {@link RuntimeException}.
 * It is thrown when there is an error during the MD5 hashing process.
 */
public class Md5HashingException extends RuntimeException {
    /**
     * Constructs a new {@code Md5HashingException} with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by
     *                the {@link Throwable#getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    @Generated
    public Md5HashingException(String message, Throwable cause) {
        super(message, cause);
    }
}

