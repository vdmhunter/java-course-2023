package edu.hw10.task1.generators;

import java.lang.reflect.Parameter;

/**
 * The {@code RandomValueGenerator} interface defines the contract for classes that generate random values
 * for parameters in a reflective manner. Implementing classes are expected to generate values of a specific type.
 *
 * @param <T> The type of the random value to be generated.
 */
public interface RandomValueGenerator<T> {
    /**
     * Generates a random value of type {@code T} for the given parameter.
     * The actual implementation may consider parameter annotations or any other relevant information.
     *
     * @param parameter The parameter for which the random value is generated. Must not be {@code null}.
     * @return A randomly generated value of type {@code T}.
     */
    T generateRandomValue(Parameter parameter);
}
