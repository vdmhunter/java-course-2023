package edu.hw8;

import edu.hw8.task1.QuoteClient;
import edu.hw8.task1.QuoteServer;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

/**
 * Tests for Homework 8, Task 1
 */
class Task1Test {
    private static Path tempFile;
    private static Thread quoteServerThread;
    private static QuoteServer quoteServer;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeAll
    public static void setUp() throws IOException {
        tempFile = Files.createTempFile("output", null);

        quoteServerThread = new Thread(() -> {
            try {
                quoteServer = new QuoteServer();
                quoteServer.start();
            } catch (IOException | InterruptedException e) {
                LOGGER.error(e);
            }
        });

        quoteServerThread.start();
    }

    @AfterAll
    public static void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);

        if (!quoteServer.isShutdown()) {
            quoteServer.shutdown();
        }

        quoteServerThread.interrupt();
    }

    @Test
    @DisplayName("Test QuoteClient's single-threaded communication with QuoteServer")
    void quoteClient_TestSingleThread() throws IOException {
        // Arrange
        String clientMessage = "самоуверенность" + System.lineSeparator() + "quit" + System.lineSeparator();
        String expected = "Такая самоуверенность, будто у тебя в руках не мышь, а пульт дистанционного " +
            "управления вселенной.";

        // Act
        try (var inputStream = new ByteArrayInputStream(clientMessage.getBytes())) {
            System.setIn(inputStream);

            QuoteClient client = new QuoteClient();
            client.start(tempFile);

            // Assert
            await().atMost(1, SECONDS).untilAsserted(() -> Assertions.assertTrue(containsInTempFile(expected)));
        }
    }

    private boolean containsInTempFile(String expected) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(tempFile)) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(expected)) {
                    return true;
                }
            }
        }

        return false;
    }
}
