package edu.hw7.task2;

import java.math.BigInteger;
import java.util.stream.IntStream;

/**
 * A utility class for calculating the factorial of a non-negative integer using both
 * single-threaded and multi thread processing approaches.
 */
public final class Factorial {
    private static final String ILLEGAL_ARGUMENT_MESSAGE = "The input integer must be greater than 0.";

    private Factorial() {
    }

    /**
     * Calculates the factorial of the given non-negative integer using a multi thread processing approach.
     *
     * @param num The non-negative integer for which to calculate the factorial.
     * @return The factorial of the input integer.
     * @throws IllegalArgumentException if the input integer is less than or equal to 0.
     */
    public static BigInteger calculateMulti(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }

        return IntStream.rangeClosed(1, num)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    /**
     * Calculates the factorial of the given non-negative integer using a single-threaded approach.
     *
     * @param num The non-negative integer for which to calculate the factorial.
     * @return The factorial of the input integer.
     * @throws IllegalArgumentException if the input integer is less than or equal to 0.
     */
    public static BigInteger calculateSingle(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }

        return IntStream.rangeClosed(1, num)
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
