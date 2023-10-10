package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for Homework 1, Task 1
 */
public class Task1Test {
    @ParameterizedTest(name = "Test {index} - Converting \"{0}\" to seconds should return {1}")
    @CsvSource({
        "0:0,0",
        "00:00,0",
        "00:01,1",
        "01:00,60",
        "13:56,836",
        "101:11,6071",
        "99999:59,5999999"
    })
    @DisplayName("Happy paths")
    void minutesToSeconds_HappyPaths(String str, int expected) {
        int actual = Task1.minutesToSeconds(str);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - Converting \"{0}\" to seconds should return -1")
    @ValueSource(strings = {
        "",
        "OO:O1",
        "0005",
        "a1:b2",
        ":",
        "10:-45",
        "-11:17",
        "-11:-29",
        "100000:59",
        "29:60"
    })
    @DisplayName("Fail paths")
    void minutesToSeconds_FailPaths(String str) {
        int actual = Task1.minutesToSeconds(str);
        int expected = -1;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test when input string is null")
    void minutesToSeconds_InputStringIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> Task1.minutesToSeconds(null));
    }
}
