package edu.hw8.task2;

import edu.common.Generated;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of a fixed-size thread pool that allows executing
 * {@link Runnable} tasks concurrently with a specified number of threads.
 */
public class FixedThreadPool implements ThreadPool {
    private final int numThreads;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;

    /**
     * Constructs a new {@code FixedThreadPool} with the specified number of threads.
     *
     * @param numThreads The number of threads in the pool.
     */
    public FixedThreadPool(int numThreads) {
        this.numThreads = numThreads;
        this.threads = new Thread[numThreads];
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    /**
     * Starts the thread pool, allowing it to process tasks concurrently.
     * Creates and starts worker threads that take tasks from the task queue
     * and execute them until interrupted.
     */
    @Override
    public void start() {
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(this::taskProcessingRoutine);
            threads[i].start();
        }
    }

    /**
     * Submits a {@link Runnable} task to the thread pool for execution.
     *
     * @param runnable The task to be executed.
     */
    @Override
    public void execute(Runnable runnable) {
        submitTaskToQueue(runnable);
    }

    /**
     * Shuts down the thread pool by interrupting all worker threads.
     */
    @Override
    public void close() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    /**
     * Creates a new instance of {@link FixedThreadPool} with the specified
     * number of threads.
     *
     * @param numThreads The number of threads in the pool.
     * @return A new {@link FixedThreadPool} instance.
     */
    @Contract("_ -> new")
    public static @NotNull FixedThreadPool create(int numThreads) {
        return new FixedThreadPool(numThreads);
    }

    /**
     * The routine executed by worker threads to process tasks from the queue.
     */
    @Generated
    private void taskProcessingRoutine() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable task = taskQueue.take();
                task.run();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Submits a task to the task queue for execution by the thread pool.
     *
     * @param runnable The task to be submitted to the queue.
     */
    @Generated
    private void submitTaskToQueue(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
