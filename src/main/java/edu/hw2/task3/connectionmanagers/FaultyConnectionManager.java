package edu.hw2.task3.connectionmanagers;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.randomgenerators.RandomGenerator;

/**
 * The {@code FaultyConnectionManager} class implements the {@link ConnectionManager} interface.
 * It always returns a {@link FaultyConnection} regardless of the input.
 */
public class FaultyConnectionManager implements ConnectionManager {
    private final RandomGenerator random;

    /**
     * Constructs a new {@code FaultyConnectionManager} with the specified {@link RandomGenerator}.
     *
     * @param random the {@link RandomGenerator} used to generate random values for the {@link FaultyConnection}.
     */
    public FaultyConnectionManager(RandomGenerator random) {
        this.random = random;
    }

    /**
     * Gets a {@link Connection}. This implementation always returns a new instance of {@link FaultyConnection}.
     *
     * @return a new {@link FaultyConnection} object.
     */
    @Override
    public Connection getConnection() {
        return new FaultyConnection(random);
    }
}
