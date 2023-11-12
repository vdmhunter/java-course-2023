package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Homework 1, Task 2
 */
class Task2Test {
    @ParameterizedTest(name = "Test {index} - For number {0}, expected digit count is {1}")
    @CsvSource({
        "0,1",
        "10,2",
        "12345,5",
        "-10,2",
        "-54321,5",
        "2147483647,10",
        "-2147483648,10"
    })
    @DisplayName("Happy paths")
    void countDigits_HappyPaths(int num, int expected) {
        int actual = Task2.countDigits(num);

        Assertions.assertEquals(expected, actual);
    }
}
