package edu.hw2.task3.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code StableConnection} class implements the {@link Connection} interface.
 * It simulates a stable connection where commands are always executed successfully.
 */
public class StableConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes a given command. In this implementation, the command is always executed successfully,
     * and a trace log message is generated indicating the successful execution of the command.
     *
     * @param command the command to be executed.
     */
    @Override
    public void execute(String command) {
        LOGGER.trace("StableConnection: Successfully executed command '{}'", command);
    }

    /**
     * Closes this connection and logs that it has been closed successfully.
     */
    @Override
    public void close() {
        LOGGER.trace("StableConnection: Connection closed successfully!");
    }
}
