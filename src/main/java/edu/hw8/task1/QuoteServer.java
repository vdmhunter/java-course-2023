package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class QuoteServer {
    private static final int PORT = 7777;
    private static final int MAX_SIMULTANEOUS_CONNECTIONS = 2;
    private static final ExecutorService POOL = Executors.newFixedThreadPool(MAX_SIMULTANEOUS_CONNECTIONS);
    private static final Semaphore SEMAPHORE = new Semaphore(MAX_SIMULTANEOUS_CONNECTIONS);

    public void start() throws IOException, InterruptedException {
        try (var serverSocket = new ServerSocket(PORT)) {
            while (!Thread.currentThread().isInterrupted()) {
                SEMAPHORE.acquire();
                Socket socket = serverSocket.accept();
                POOL.execute(new QuoteClientHandler(socket, SEMAPHORE));
            }
        }
    }

    public void shutdown() {
        POOL.shutdown();
    }

    public boolean isShutdown() {
        return POOL.isShutdown();
    }
}
