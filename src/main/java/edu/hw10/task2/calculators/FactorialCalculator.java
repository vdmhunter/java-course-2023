package edu.hw10.task2.calculators;

import edu.hw10.task2.Cache;

/**
 * The {@code FactorialCalculator} interface defines a contract for calculating factorials.
 * Implementations of this interface may provide caching functionality using the {@link Cache} annotation.
 * The default behavior is that caching is disabled (persist is set to false) unless specified otherwise.
 */
public interface FactorialCalculator {
    /**
     * Calculates the factorial of the given number.
     *
     * @param number The non-negative integer for which the factorial is to be calculated.
     * @return The factorial of the given number.
     * @throws IllegalArgumentException If the input number is negative.
     */
    @Cache() // persist == false
    long factorial(int number);
}
