package edu.hw6.task6;

/**
 * The {@code Protocol} enum represents the communication protocols used in port scanning.
 * It includes two constants: {@code TCP} and {@code UDP}.
 * <p>
 * The enum is used in the {@link PortScanner} class to distinguish between TCP and UDP ports
 * during the port scanning process.
 * </p>
 *
 * @see PortScanner
 */
public enum Protocol {
    /**
     * Transmission Control Protocol.
     * Represents the TCP communication protocol used in port scanning.
     */
    TCP,
    /**
     * User Datagram Protocol.
     * Represents the UDP communication protocol used in port scanning.
     */
    UDP
}
