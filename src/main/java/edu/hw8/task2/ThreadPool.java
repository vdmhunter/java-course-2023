package edu.hw8.task2;

/**
 * An interface representing a simple thread pool that can execute
 * {@link Runnable} tasks concurrently.
 *
 * <p>The implementing classes should provide a mechanism to start the thread pool,
 * submit tasks for execution, and manage the lifecycle of the pool.
 *
 * <p>Implementations may also extend {@link AutoCloseable} to allow for proper
 * cleanup and shutdown of resources when the pool is no longer needed.
 */
public interface ThreadPool extends AutoCloseable {
    /**
     * Starts the thread pool, allowing it to process tasks concurrently.
     * The implementation should create and start worker threads that
     * take tasks and execute them until interrupted or the pool is closed.
     */
    void start();

    /**
     * Submits a {@link Runnable} task to the thread pool for execution.
     *
     * @param runnable The task to be executed.
     */
    void execute(Runnable runnable);

    /**
     * Closes the thread pool and performs cleanup operations.
     */
    void close();
}
