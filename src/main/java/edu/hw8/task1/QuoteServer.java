package edu.hw8.task1;

import edu.common.Generated;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * The {@code QuoteServer} class represents a simple server that accepts incoming client connections,
 * manages them using a thread pool, and delegates the handling of client requests to instances
 * of the {@link QuoteClientHandler} class. The server can be started, and its thread pool can be
 * shut down gracefully.
 */
public class QuoteServer {
    private static final int PORT = 7777;
    private static final int MAX_SIMULTANEOUS_CONNECTIONS = 2;
    private static final ExecutorService POOL = Executors.newFixedThreadPool(MAX_SIMULTANEOUS_CONNECTIONS);
    private static final Semaphore SEMAPHORE = new Semaphore(MAX_SIMULTANEOUS_CONNECTIONS);

    /**
     * Starts the {@code QuoteServer}, creating a ServerSocket and accepting incoming client connections.
     * Each connection is handled by a {@link QuoteClientHandler} instance from the thread pool.
     *
     * @throws IOException If an I/O error occurs while creating the ServerSocket or accepting connections.
     */
    public void start() throws IOException {
        handleIncomingConnections();
    }

    /**
     * Shuts down the thread pool gracefully, allowing ongoing tasks to complete before terminating.
     */
    public void shutdown() {
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
     * Handles incoming client connections, creating a ServerSocket and accepting connections.
     * Each connection is delegated to a {@link QuoteClientHandler} instance from the thread pool.
     *
     * @throws IOException If an I/O error occurs while accepting connections.
     */
    @Generated
    private static void handleIncomingConnections() throws IOException {
        try (var serverSocket = new ServerSocket(PORT)) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                SEMAPHORE.acquire();
                POOL.execute(new QuoteClientHandler(socket, SEMAPHORE));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
