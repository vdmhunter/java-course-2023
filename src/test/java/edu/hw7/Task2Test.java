package edu.hw7;

import edu.hw7.task2.Factorial;
import java.math.BigInteger;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for Homework 7, Task 2
 */
class Task2Test {
    @ParameterizedTest(name = "Test {index} - For number {0}, expected factorial is {1}")
    @CsvSource({
        "5,120",
        "10,3628800",
        "20,2432902008176640000"
    })
    @DisplayName("Happy paths for Factorial.calculateMulti")
    void factorialCalculateMulti_HappyPaths(int num, String expectedFactorial) {
        //Arrange
        var expected = new BigInteger(expectedFactorial);

        //Act
        var actual = Factorial.calculateMulti(num);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - For number {0}, expecting IllegalArgumentException")
    @ValueSource(strings = {
        "-5",
        "-10",
        "-20"
    })
    @DisplayName("Fail paths for Factorial.calculateMulti")
    void factorialCalculateMulti_FailPaths(int num) {
        // Arrange
        var expectedType = IllegalArgumentException.class;

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> Factorial.calculateMulti(num));
    }

    @ParameterizedTest(name = "Test {index} - For number {0}, expected factorial is {1}")
    @CsvSource({
        "5,120",
        "10,3628800",
        "20,2432902008176640000"
    })
    @DisplayName("Happy paths for Factorial.calculateSingle")
    void factorialCalculateSingle_HappyPaths(int num, String expectedFactorial) {
        //Arrange
        var expected = new BigInteger(expectedFactorial);

        //Act
        var actual = Factorial.calculateSingle(num);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - For number {0}, expecting IllegalArgumentException")
    @ValueSource(strings = {
        "-5",
        "-10",
        "-20"
    })
    @DisplayName("Fail paths for Factorial.calculateSingle")
    void factorialCalculateSingle_FailPaths(int num) {
        // Arrange
        var expectedType = IllegalArgumentException.class;

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> Factorial.calculateMulti(num));
    }

    @Test
    @DisplayName("Performance Test: Factorial Calculation (Parallel vs Single-threaded)")
    void factorialCalculate_TestPerfomance() {
        // Arrange
        int num = 1000;

        // Act
        long startTimeSingle = System.nanoTime();
        Factorial.calculateSingle(num);
        long endTimeSingle = System.nanoTime();
        Duration durationSingle = Duration.ofNanos(endTimeSingle - startTimeSingle);

        long startTimeMulti = System.nanoTime();
        Factorial.calculateMulti(num);
        long endTimeMulti = System.nanoTime();
        Duration durationMulti = Duration.ofNanos(endTimeMulti - startTimeMulti);

        // Assert
        Assertions.assertTrue(durationMulti.compareTo(durationSingle) < 1);
    }
}
