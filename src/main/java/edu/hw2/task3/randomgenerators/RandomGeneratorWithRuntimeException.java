package edu.hw2.task3.randomgenerators;

/**
 * This class implements the {@code RandomGenerator} interface and overrides the
 * {@link RandomGenerator#nextDouble()} method.
 * Unlike typical RandomGenerators, this class throws a {@link RuntimeException}
 * whenever {@link RandomGeneratorWithRuntimeException#nextDouble()} is called.
 * This is useful in testing scenarios where you need a {@code RandomGenerator} that fails.
 */
public class RandomGeneratorWithRuntimeException implements RandomGenerator {
    /**
     * Overrides the {@link RandomGenerator#nextDouble()} method from the {@code RandomGenerator} interface.
     * This implementation does not return a value but instead throws a {@link RuntimeException}.
     *
     * @return This method does not return a value.
     * @throws RuntimeException always.
     */
    @Override
    public double nextDouble() {
        throw new RuntimeException();
    }
}
