package edu.hw10.task2.calculators;

/**
 * The {@code FibCalculatorImpl} class implements the {@link FibCalculator} interface
 * and provides a method for calculating Fibonacci numbers using a recursive algorithm.
 */
public class FibCalculatorImpl implements FibCalculator {
    /**
     * Calculates the Fibonacci number at the given index using a recursive algorithm.
     *
     * @param number The index of the Fibonacci number to be calculated.
     * @return The Fibonacci number at the given index.
     * @throws IllegalArgumentException If the input number is negative.
     */
    public long fib(int number) {
        if (number <= 1) {
            return number;
        }

        return fib(number - 1) + fib(number - 2);
    }
}
