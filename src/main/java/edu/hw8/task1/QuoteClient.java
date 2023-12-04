package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code QuoteClient} class represents a client that connects to a remote server
 * to receive and store quotes. It allows the user to input quotes through the console
 * and sends them to the server. The received quotes are logged and appended to a specified file.
 * The client can be terminated by entering the quit command.
 */
public class QuoteClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 7777;
    private static final int BUFFER_SIZE = 1024;
    public static final String QUIT_COMMAND = "quit";
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Starts the {@code QuoteClient}, establishing a connection to the server
     * and allowing the user to input quotes until the quit command is entered.
     * Quotes are sent to the server, logged, and appended to the specified file.
     *
     * @param file The path to the file where received quotes will be stored.
     * @throws IOException If an I/O error occurs while communicating with the server.
     */
    public void start(Path file) throws IOException {
        try (var socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            var scanner = new Scanner(System.in);
            var buffer = new byte[BUFFER_SIZE];

            while (true) {
                if (scanner.hasNextLine()) {
                    String message = scanner.nextLine();

                    if (QUIT_COMMAND.equalsIgnoreCase(message)) {
                        break;
                    }

                    outputStream.write(message.getBytes());
                    int bytesRead = inputStream.read(buffer);
                    var str = new String(buffer, 0, bytesRead);

                    Files.write(file, str.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    LOGGER.info(str);
                }
            }
        }
    }
}
