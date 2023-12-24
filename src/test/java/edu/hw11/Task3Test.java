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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * tests for homework 11, task 3
 */
class Task3Test {
    private static final Logger LOGGER = LogManager.getLogger();

    @ParameterizedTest(name = "Test {index} - Iterative Fibonacci Calculation using ByteBuddy: n={0}, expected={1}")
    @CsvSource({
        "1,1",
        "2,1",
        "3,2",
        "4,3",
        "5,5",
        "6,8",
        "7,13",
        "8,21",
        "9,34",
        "10,55",
    })
    @DisplayName("Test iterative Fibonacci Calculation using ByteBuddy")
    void ByteBuddy_TestIterativeFibonacci(int n, long expected) {
        // Arrange
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
            actual = (long) fibMethod.invoke(dynamicType, n);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
