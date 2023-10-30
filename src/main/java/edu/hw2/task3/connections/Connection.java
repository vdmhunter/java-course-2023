package edu.hw2.task3.connections;

/**
 * The {@code Connection} interface defines a contract for executing commands and managing resources.
 * Implementations of this interface are responsible for defining how commands are executed.
 * This interface extends {@link AutoCloseable}, so any class implementing this interface
 * must also implement the {@link AutoCloseable#close()} method from the {@link AutoCloseable} interface.
 */
public interface Connection extends AutoCloseable {
    /**
     * Executes a given command. The exact behavior of this method depends on the implementation.
     *
     * @param command the command to be executed.
     */
    void execute(String command);

    /**
     * Closes the connection and releases associated resources.
     */
    void close();
}
