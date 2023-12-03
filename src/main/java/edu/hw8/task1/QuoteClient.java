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

public class QuoteClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 7777;
    private static final int BUFFER_SIZE = 1024;
    public static final String QUIT_COMMAND = "quit";
    private static final Logger LOGGER = LogManager.getLogger();

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
