package edu.hw10.task2.calculators;

/**
 * The {@code FactorialCalculatorImpl} class implements the {@link FactorialCalculator} interface
 * and provides a method for calculating the factorial of a given non-negative integer.
 */
public class FactorialCalculatorImpl implements FactorialCalculator {
    /**
     * Calculates the factorial of the given non-negative integer.
     *
     * @param number The non-negative integer for which the factorial is to be calculated.
     * @return The factorial of the given number.
     * @throws IllegalArgumentException If the input number is negative.
     */
    @Override
    public long factorial(int number) {
        long result = 1;

        for (int i = 2; i <= number; i++) {
            result *= i;
        }

        return result;
    }
}
