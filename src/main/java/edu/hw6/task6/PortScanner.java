package edu.hw6.task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code PortScanner} class provides functionality for scanning ports on a host machine.
 * It includes methods for scanning TCP and UDP ports, determining their status (open or closed),
 * and printing the results to the console.
 * <p>
 * The {@code PortScanner} class relies on the {@link Status} enum to represent the status of a port,
 * the {@link Protocol} enum to distinguish between TCP and UDP protocols, and the {@link Color} enum
 * to add color formatting to the console output.
 * </p>
 * <p>
 * The class also includes a predefined set of well-known ports with their corresponding services in the
 * {@link #PORTS} map. The scanning process covers a predefined range of ports (0 to 49150) and logs the
 * results using Log4j.
 * </p>
 */
public final class PortScanner {
    private PortScanner() {
    }

    private static final String HEADER = "Protocol  Port  Status  Service\n";
    private static final String LINE_FORMAT = "%-9s %-5d %-22s %-25s%n";
    private static final String RESET = "\u001B[0m";
    private static final IntStream PORT_RANGE = IntStream.range(0, 49151);
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<Integer, String> PORTS = Map.of(
        21, "FTP",
        22, "SSH",
        23, "Telnet",
        25, "SMTP",
        53, "DNS",
        80, "HTTP",
        110, "POP3",
        143, "IMAP",
        443, "HTTPS",
        1200, "Steam"
    );

    /**
     * Initiates the scanning process to identify open and closed ports on the host machine.
     * The method prints the results to the console using Log4j.
     *
     * @see #scanTcpPort(int)
     * @see #scanUdpPort(int)
     */
    public static void scanPorts() {
        String string = setColor(HEADER, Color.BLUE);
        LOGGER.info(string);

        PORT_RANGE.forEach(port -> {
            scanTcpPort(port);
            scanUdpPort(port);
        });
    }

    /**
     * Scans the specified TCP port, determines its status (open or closed),
     * and logs the result to the console using Log4j.
     *
     * @param port The TCP port to be scanned.
     * @see #getTcpPortStatus(int)
     * @see #scanPort(Protocol, int, Status)
     */
    private static void scanTcpPort(int port) {
        Status status = getTcpPortStatus(port);
        scanPort(Protocol.TCP, port, status);
    }

    /**
     * Scans the specified UDP port, determines its status (open or closed),
     * and logs the result to the console using Log4j.
     *
     * @param port The UDP port to be scanned.
     * @see #getUdpPortStatus(int)
     * @see #scanPort(Protocol, int, Status)
     */
    private static void scanUdpPort(int port) {
        Status status = getUdpPortStatus(port);
        scanPort(Protocol.UDP, port, status);
    }

    /**
     * Determines the status of the specified TCP port.
     * Attempts to create a {@link ServerSocket} on the specified port,
     * and if successful, the port is considered open; otherwise, it is closed.
     *
     * @param port The TCP port to be checked.
     * @return The status of the TCP port (OPEN or CLOSED).
     */
    private static Status getTcpPortStatus(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.getLocalPort();

            return Status.OPEN;
        } catch (Exception e) {
            return Status.CLOSED;
        }
    }

    /**
     * Determines the status of the specified UDP port.
     * Attempts to create a {@link DatagramSocket} on the specified port,
     * and if successful, the port is considered open; otherwise, it is closed.
     *
     * @param port The UDP port to be checked.
     * @return The status of the UDP port (OPEN or CLOSED).
     */
    private static Status getUdpPortStatus(int port) {
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            serverSocket.getLocalPort();

            return Status.OPEN;
        } catch (Exception e) {
            return Status.CLOSED;
        }
    }

    /**
     * Logs the details of a scanned port (Protocol, Port, Status, and Service) to the console using Log4j.
     *
     * @param protocol The protocol of the scanned port (TCP or UDP).
     * @param port     The port number.
     * @param status   The status of the scanned port (OPEN or CLOSED).
     * @see #setColor(String, Color)
     */
    private static void scanPort(@NotNull Protocol protocol, int port, @NotNull Status status) {
        String service = PORTS.getOrDefault(port, "");
        String color = setColor(status.toString(), getStatusColor(status));
        String string = String.format(
            LINE_FORMAT,
            protocol,
            port,
            color,
            service
        );

        if (!service.isEmpty()) {
            LOGGER.info(string);
        }
    }

    /**
     * Sets the specified color for the given string. This method is used to add color formatting to console output.
     * The color is applied using ANSI escape codes, and the resulting colored string is returned.
     *
     * @param string The string to which the color should be applied.
     * @param color  The color to be applied (RESET, RED, GREEN, or BLUE).
     * @return The input string with the specified color applied.
     * @throws NullPointerException if the input string or color is {@code null}.
     * @see Color
     */
    private static String setColor(String string, @NotNull Color color) {
        return switch (color) {
            case RED -> Color.RED.getValue() + string + RESET;
            case GREEN -> Color.GREEN.getValue() + string + RESET;
            case BLUE -> Color.BLUE.getValue() + string + RESET;
        };
    }

    /**
     * Retrieves the color associated with the specified status. This method is used to determine the color
     * to be applied to the console output based on the status of a scanned port (OPEN or CLOSED).
     *
     * @param status The status of a scanned port (OPEN or CLOSED).
     * @return The color associated with the specified status (GREEN for OPEN, RED for CLOSED).
     * @throws NullPointerException if the input status is {@code null}.
     * @see Status
     * @see Color
     */
    @Contract(pure = true)
    private static Color getStatusColor(@NotNull Status status) {
        return switch (status) {
            case OPEN -> Color.GREEN;
            case CLOSED -> Color.RED;
        };
    }
}
