package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.reflect.Parameter;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code IntegerRandomValueGenerator} class implements the {@code RandomValueGenerator} interface
 * to generate random integer values within specified constraints using annotations.
 * It is specifically designed to generate random values for parameters annotated with {@code Min} and {@code Max}.
 */
public class IntegerRandomValueGenerator implements RandomValueGenerator<Integer> {
    private static final Random RANDOM = new Random();

    /**
     * Generates a random integer value based on the constraints specified by the {@code Min} and {@code Max}
     * annotations on the given parameter.
     *
     * @param parameter The parameter for which the random value is generated. Must not be {@code null}.
     * @return A randomly generated integer value within the specified constraints.
     */
    @Override
    public Integer generateRandomValue(@NotNull Parameter parameter) {
        Min min = parameter.getAnnotation(Min.class);
        Max max = parameter.getAnnotation(Max.class);
        int minValue = (min != null) ? min.value() : Integer.MIN_VALUE;
        int maxValue = (max != null) ? max.value() : Integer.MAX_VALUE - 1;

        return RANDOM.nextInt(minValue, maxValue + 1);
    }
}
