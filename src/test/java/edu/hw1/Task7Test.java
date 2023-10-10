package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Homework 1, Task 7
 */
public class Task7Test {
    @ParameterizedTest(name = "Test {index} - rotateLeft: n={0}, shift={1}, expected={2}")
    @CsvSource({
        "16,1,1",
        "17,2,6",
        "102,0,102",
        "102,1,77",
        "102,2,27",
        "2147483647,10,2147483647",
    })
    @DisplayName("Happy paths for rotateLeft")
    void rotateLeft_HappyPaths(int n, int shift, int expected) {
        int actual = Task7.rotateLeft(n, shift);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - rotateRight: n={0}, shift={1}, expected={2}")
    @CsvSource({
        "8,1,4",
        "85,0,85",
        "85,1,106",
        "85,2,53",
        "85,3,90",
        "2147483647,10,2147483647",
    })
    @DisplayName("Happy paths for rotateRight")
    void rotateRight_HappyPaths(int n, int shift, int expected) {
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
