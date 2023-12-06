package edu.hw2.task3.connections;

import edu.hw2.task3.exceptions.ConnectionException;
import edu.hw2.task3.randomgenerators.RandomGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code FaultyConnection} class implements the {@link Connection} interface.
 * It simulates a connection that can fail when executing commands.
 * The decision to fail is determined by a {@link RandomGenerator}: if the next double value is less than a threshold,
 * the execute method will throw a {@link ConnectionException}.
 */
public class FaultyConnection implements Connection {
    private final RandomGenerator random;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double THRESHOLD = 0.5;

    public FaultyConnection(RandomGenerator random) {
        this.random = random;
    }

    /**
     * Executes a given command. If the next double value from the {@link RandomGenerator} is less than the threshold,
     * it throws a ConnectionException; otherwise, it logs the successful execution of the command.
     *
     * @param command the command to be executed.
     * @throws ConnectionException if the next double value from the {@link RandomGenerator} is less than the threshold.
     */
    @Override
    public void execute(String command) {
        if (random.nextDouble() < THRESHOLD) {
            throw new ConnectionException("FaultyConnection: Failed to execute command: '" + command + "'");
        }

        LOGGER.trace("FaultyConnection: Successfully executed command '{}'", command);
    }

    /**
     * Closes this connection and logs that it has been closed successfully.
     */
    @Override
    public void close() {
        LOGGER.trace("FaultyConnection: Connection closed successfully!");
    }
}
