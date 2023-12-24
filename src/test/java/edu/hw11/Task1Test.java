package edu.hw11;

import java.lang.reflect.Constructor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * tests for homework 11, task 1
 */
class Task1Test {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Test ByteBuddy dynamically generated toString() returns 'Hello, ByteBuddy!'")
    void ByteBuddy_TestToString() {
        // Arrange
        Object instance;

        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        String expected = "Hello, ByteBuddy!";
        String actual = "";

        // Act
        try {
            Constructor<?> constructor = dynamicType.getDeclaredConstructor();
            constructor.setAccessible(true);
            instance = constructor.newInstance();

            actual = instance.toString();
        } catch (Exception e) {
            LOGGER.error(e);
        }

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
