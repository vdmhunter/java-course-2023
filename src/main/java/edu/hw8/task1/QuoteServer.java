package edu.hw8.task1;

import edu.common.Generated;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code QuoteServer} class is a server that manages client connections using a thread pool.
 * It accepts incoming connections, delegates request handling to {@link QuoteClientHandler} instances,
 * and can be gracefully started and shut down.
 */
public class QuoteServer {
    private ServerSocket serverSocket;
    private static final int PORT = 7777;
    private static final int MAX_SIMULTANEOUS_CONNECTIONS = 2;
    private static final ExecutorService POOL = Executors.newFixedThreadPool(MAX_SIMULTANEOUS_CONNECTIONS);
    private static final Semaphore SEMAPHORE = new Semaphore(MAX_SIMULTANEOUS_CONNECTIONS);
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Initiates the {@code QuoteServer}, sets up a ServerSocket, and accepts incoming client connections.
     * Each connection is managed by a {@link QuoteClientHandler} instance from the thread pool.
     *
     * @throws IOException If an I/O error occurs during the creation of the ServerSocket or
     * while accepting connections.
     */
    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);

        try {
            while (!Thread.currentThread().isInterrupted()) {
                handleClientConnection(serverSocket);
            }
        } finally {
            LOGGER.info("Server has stopped accepting new connections.");
        }
    }

    /**
     * Gracefully shuts down the thread pool, allowing ongoing tasks to finish before termination.
     * Also closes the server socket to stop accepting new connections.
     *
     * @throws IOException If an I/O error occurs while closing the server socket.
     */
    public void shutdown() throws IOException {
        serverSocket.close();
        POOL.shutdown();
    }

    /**
     * Checks if the thread pool has been shut down.
     *
     * @return {@code true} if the thread pool is shut down, {@code false} otherwise.
     */
    public boolean isShutdown() {
        return POOL.isShutdown();
    }

    /**
     * Accepts a new client connection, acquires a permit from the semaphore, and assigns a {@link QuoteClientHandler}
     * to manage the connection in the thread pool.
     *
     * @param serverSocket The server socket to accept connections from.
     */
    @Generated
    private static void handleClientConnection(@NotNull ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            SEMAPHORE.acquire();
            POOL.execute(new QuoteClientHandler(socket, SEMAPHORE));
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        }
    }
}
