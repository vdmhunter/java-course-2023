package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A thread-safe counter implementation using {@link AtomicInteger}.
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
     * Increments the counter by 1 in a thread-safe manner.
     */
    public int getCount() {
        return count.get();
    }
}
