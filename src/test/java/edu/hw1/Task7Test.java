package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Homework 1, Task 7
 */
class Task7Test {
    @ParameterizedTest(name = "Test {index} - rotateLeft: n=0b{0}, shift={1}, expected=0b{2}")
    @CsvSource({
        "10000,1,00001",
        "10001,2,00110",
        "1100110,0,1100110",
        "1100110,1,1001101",
        "1100110,2,0011011",
        "1111111111111111111111111111111,10,1111111111111111111111111111111",
    })
    @DisplayName("Happy paths for rotateLeft")
    void rotateLeft_HappyPaths(String nStr, int shift, String expectedStr) {
        int n = Integer.parseInt(nStr, 2);
        int expected = Integer.parseInt(expectedStr, 2);
        int actual = Task7.rotateLeft(n, shift);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - rotateRight: n=0b{0}, shift={1}, expected=0b{2}")
    @CsvSource({
        "1000,1,0100",
        "1010101,0,1010101",
        "1010101,1,1101010",
        "1010101,2,0110101",
        "1010101,3,1011010",
        "1111111111111111111111111111111,10,1111111111111111111111111111111",
    })
    @DisplayName("Happy paths for rotateRight")
    void rotateRight_HappyPaths(String nStr, int shift, String expectedStr) {
        int n = Integer.parseInt(nStr, 2);
        int expected = Integer.parseInt(expectedStr, 2);
        int actual = Task7.rotateRight(n, shift);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - Testing input: n={0}, shift={1} expecting IllegalArgumentException")
    @CsvSource({
        "-1,1",
        "16,-1",
        "-85,-5",
        "-2147483648,10",
    })
    @DisplayName("Fail paths for rotateLeft")
    void rotateLeft_FailPaths(int num, int shift) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task7.rotateLeft(num, shift));
    }

    @ParameterizedTest(name = "Test {index} - Testing input: n={0}, shift={1} expecting IllegalArgumentException")
    @CsvSource({
        "-1,1",
        "16,-1",
        "-85,-5",
        "-2147483648,10",
    })
    @DisplayName("Fail paths for rotateRight")
    void rotateRight_FailPaths(int num, int shift) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task7.rotateRight(num, shift));
    }
}
