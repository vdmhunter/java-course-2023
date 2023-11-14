package edu.hw2.task3.randomgenerators;

/**
 * The {@code FailingRandomGenerator} class implements the {@code RandomGenerator} interface
 * and overrides the {@link RandomGenerator#nextDouble()} method.
 * Unlike typical RandomGenerators, this class throws a {@link IllegalStateException}
 * whenever {@link FailingRandomGenerator#nextDouble()} is called.
 * This is useful in testing scenarios where you need a {@code RandomGenerator} that fails.
 */
public class FailingRandomGenerator implements RandomGenerator {
    /**
     * Overrides the {@link RandomGenerator#nextDouble()} method from the {@code RandomGenerator} interface.
     * This implementation does not return a value but instead throws a {@link IllegalStateException}.
     *
     * @return This method does not return a value.
     * @throws IllegalStateException always.
     */
    @Override
    public double nextDouble() {
        throw new IllegalStateException("FailingRandomGenerator: Unable to generate a random number");
    }
}
