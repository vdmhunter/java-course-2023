package edu.hw10.task1.generators;

import java.lang.reflect.Parameter;
import java.util.Random;

/**
 * The {@code StringRandomValueGenerator} class implements the {@code RandomValueGenerator} interface
 * to generate random string values of a specified length.
 * It is designed to generate random values for parameters of type {@code String}.
 */
public class StringRandomValueGenerator implements RandomValueGenerator<String> {
    private static final int LEFT_LIMIT = 'a';
    private static final int RIGHT_LIMIT = 'z';
    private static final int MIN_STRING_LENGTH = 1;
    private static final int MAX_STRING_LENGTH = 10;
    private static final Random RANDOM = new Random();

    /**
     * Generates a random string value of a specified length for the given parameter.
     *
     * @param parameter The parameter for which the random string value is generated. Must not be {@code null}.
     * @return A randomly generated string value.
     */
    @Override
    public String generateRandomValue(Parameter parameter) {
        int targetStringLength = RANDOM.nextInt(MIN_STRING_LENGTH, MAX_STRING_LENGTH + 1);

        return RANDOM.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }
}
