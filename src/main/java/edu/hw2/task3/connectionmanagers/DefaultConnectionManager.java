package edu.hw2.task3.connectionmanagers;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.StableConnection;
import edu.hw2.task3.randomgenerators.RandomGenerator;

/**
 * The {@code DefaultConnectionManager} class implements the {@link ConnectionManager} interface.
 * It uses a {@link RandomGenerator} to decide which type of {@link Connection} to return:
 * a {@link FaultyConnection} or a {@link StableConnection}.
 */
public class DefaultConnectionManager implements ConnectionManager {
    private final RandomGenerator randomGenerator;
    private static final double THRESHOLD = 0.5;

    /**
     * Constructs a new {@link DefaultConnectionManager} with the specified {@link RandomGenerator}.
     *
     * @param randomGenerator the {@link RandomGenerator} used to decide which type of {@link Connection} to return.
     */
    public DefaultConnectionManager(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    /**
     * Gets a {@link Connection}. If the next double value from the {@link RandomGenerator} is less than the threshold,
     * it returns a {@link FaultyConnection}; otherwise, it returns a {@link StableConnection}.
     *
     * @return a {@link Connection} object, which is either a {@link FaultyConnection} or a {@link StableConnection}.
     */
    @Override
    public Connection getConnection() {
        if (randomGenerator.nextDouble() < THRESHOLD) {
            return new FaultyConnection(randomGenerator);
        }

        return new StableConnection();
    }
}
