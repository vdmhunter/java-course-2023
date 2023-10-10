package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Homework 1, Task 4
 */
public class Task4Test {
    @ParameterizedTest(name = "Test {index} - Fixing string \"{0}\" should return \"{1}\"")
    @SuppressWarnings("SpellCheckingInspection")
    @CsvSource({
        "123456,214365",
        "hTsii  s aimex dpus rtni.g,This is a mixed up string.",
        "badce,abcde",
        "a,a",
        "' ',' '",
    })
    @DisplayName("Happy paths")
    void fixString_HappyPaths(String str, String expected) {
        String actual = Task4.fixString(str);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test when input string is null")
    void fixString_InputStringIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> Task4.fixString(null));
    }

    @Test
    @DisplayName("Test when input string is empty")
    void fixString_InputStringIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task4.fixString(""));
    }
}
