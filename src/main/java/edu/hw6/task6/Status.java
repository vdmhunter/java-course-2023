package edu.hw6.task6;

/**
 * The {@code Status} enum represents the possible states of a scanned port during port scanning.
 * It includes two constants: {@code OPEN} and {@code CLOSED}.
 * <p>
 * The enum is used in the {@link PortScanner} class to indicate whether a port is open or closed.
 * The status is determined based on the results of attempting to create sockets on the specified ports.
 * </p>
 *
 * @see PortScanner
 */
public enum Status {
    /**
     * Open status.
     * Indicates that a port is open during port scanning.
     */
    OPEN,
    /**
     * Closed status.
     * Indicates that a port is closed during port scanning.
     */
    CLOSED
}
