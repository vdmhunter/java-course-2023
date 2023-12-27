package edu.hw11;

import edu.hw11.task2.ArithmeticUtils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * tests for homework 11, task 2
 */
class Task2Test {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Test ByteBuddy dynamically modified sum() method behavior")
    void byteBuddy_TestSumModification() {
        // Arrange
        int a = 2;
        int b = 3;
        int expected = 6;
        int actual = 0;

        Class<?> dynamicType = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(FixedValue.value(a * b))
            .make()
            .load(ArithmeticUtils.class.getClassLoader())
            .getLoaded();

        // Act
        try {
            ArithmeticUtils utils = (ArithmeticUtils) dynamicType.getDeclaredConstructor().newInstance();
            actual = utils.sum(a, b);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
