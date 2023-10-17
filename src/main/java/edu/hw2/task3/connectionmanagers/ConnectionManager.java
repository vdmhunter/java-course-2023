package edu.hw2.task3.connectionmanagers;

import edu.hw2.task3.connections.Connection;

/**
 * The {@code ConnectionManager} interface defines a contract for managing connections.
 * Implementations of this interface are responsible for creating and providing connections.
 */
public interface ConnectionManager {
    /**
     * Gets a connection.
     *
     * @return a {@link Connection} object.
     */
    Connection getConnection();
}
