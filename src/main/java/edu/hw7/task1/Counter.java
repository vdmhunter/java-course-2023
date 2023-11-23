package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class implements a thread-safe counter using the {@link AtomicInteger} class.
 * The counter can be incremented by one in a thread-safe manner.
 */
public class Counter {
    private final AtomicInteger count = new AtomicInteger();

    /**
     * Increments the counter by 1 in a thread-safe manner.
     */
    public void increment() {
        count.getAndIncrement();
    }

    /**
     * Retrieves the current count of the counter in a thread-safe manner.
     *
     * @return The current count of the counter.
     */
    public int getCount() {
        return count.get();
    }
}
