package edu.hw2.task3.randomgenerators;

/**
 * The {@code RandomGenerator} interface defines a contract for generating random double values.
 */
public interface RandomGenerator {
    /**
     * Generates a random double value. The exact range and distribution of the generated values
     * depends on the implementation. Typically, this method will return a value in the range [0.0, 1.0).
     *
     * @return a random double value.
     */
    double nextDouble();
}
