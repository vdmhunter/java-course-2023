package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for Homework 1, Task 6
 */
public class Task6Test {
    @ParameterizedTest(name = "Test {index} - Kaprekar’s constant test for number {0}, expected result is {1}")
    @CsvSource({
        "6621,5",
        "6554,4",
        "1234,3",
        "3524,3",
        "8991,3",
        "2005,7",
        "4102,7",
        "6174,0"
    })
    @DisplayName("Happy paths")
    void countK_HappyPaths(int num, int expected) {
        int actual = Task6.countK(num);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - Testing invalid input {0}, expecting IllegalArgumentException")
    @ValueSource(strings = {
        "1000",
        "1111",
        "12345",
        "100",
        "-1234"
    })
    @DisplayName("Fail paths")
    void countK_FailPaths(int num) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task6.countK(num));
    }
}
