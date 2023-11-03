package edu.hw2.task3.randomgenerators;

import java.util.Random;

/**
 * This class implements the {@code RandomGenerator} interface and provides a way to generate
 * random double values with a specific seed. This can be useful in scenarios where
 * reproducible randomness is required, such as in testing or debugging.
 */
public class RandomGeneratorWithSeed implements RandomGenerator {
    private final Random random;

    /**
     * Constructs a new {@code RandomGeneratorWithSeed} that uses the specified seed.
     *
     * @param seed the seed for this random number generator.
     */
    public RandomGeneratorWithSeed(long seed) {
        this.random = new Random(seed);
    }

    @Override
    public double nextDouble() {
        return random.nextDouble();
    }
}
