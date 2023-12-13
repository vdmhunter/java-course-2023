package edu.hw10.task2.calculators;

import edu.hw10.task2.Cache;

/**
 * The {@code FibCalculator} interface defines a contract for calculating Fibonacci numbers.
 * Implementations of this interface may provide caching functionality using the {@link Cache} annotation.
 * The default behavior is that caching is enabled (persist is set to true) unless specified otherwise.
 */
public interface FibCalculator {
    /**
     * Calculates the Fibonacci number at the given index.
     *
     * @param number The index of the Fibonacci number to be calculated.
     * @return The Fibonacci number at the given index.
     * @throws IllegalArgumentException If the input number is negative.
     */
    @Cache(persist = true)
    long fib(int number);
}

