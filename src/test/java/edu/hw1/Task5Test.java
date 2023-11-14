package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for Homework 1, Task 5
 */
class Task5Test {
    @ParameterizedTest(name = "Test {index} - Checking palindrome descendant for number {0}, expected result is {1}")
    @CsvSource({
        "11211230,true",
        "13001120,true",
        "23336014,true",
        "11,true",
        "12,false"
    })
    @DisplayName("Happy paths")
    void isPalindromeDescendant_HappyPaths(int num, boolean expected) {
        // Act
        boolean actual = Task5.isPalindromeDescendant(num);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - Testing invalid input {0}, expecting IllegalArgumentException")
    @ValueSource(strings = {
        "-1",
        "0",
        "5",
        "123",
        "34512"
    })
    @DisplayName("Fail paths")
    void isPalindromeDescendant_FailPaths(int num) {
        // Arrange
        var expectedType = IllegalArgumentException.class;

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> Task5.isPalindromeDescendant(num));
    }
}
