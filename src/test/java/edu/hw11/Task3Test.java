package edu.hw11;

import edu.hw11.task3.FibonacciByteCodeAppender;
import java.lang.reflect.Method;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * tests for homework 11, task 3
 */
class Task3Test {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Test iterative Fibonacci Calculation using ByteBuddy")
    void ByteBuddy_TestIterativeFibonacci() {
        // Arrange
        long expected = 144L;

        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("IterativeFibonacci")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(new FibonacciByteCodeAppender()))
            .make()
            .load(ClassLoader.getSystemClassLoader())
            .getLoaded();

        long actual = 0L;

        // Act
        try {
            Method fibMethod = dynamicType.getMethod("fib", int.class);
            actual = (long) fibMethod.invoke(dynamicType, 12);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
