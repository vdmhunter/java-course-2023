package edu.hw5;

import edu.hw5.task7.RegexPatternMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for Homework 5, Task 7
 */
class Task7Test {
    @ParameterizedTest(name = "Test {index} - \"{0}\" matches at least 3 characters with the 3rd one being '0'")
    @ValueSource(strings = {
        "000",
        "010",
        "1000",
        "10010"
    })
    @DisplayName("Happy paths for method \"matchesAtLeastThreeCharsThirdIsZero\"")
    void matchesAtLeastThreeCharsThirdIsZero_HappyPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesAtLeastThreeCharsThirdIsZero(str);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - \"{0}\" matches strings with the same start and end character '0' or '1'")
    @ValueSource(strings = {
        "000",
        "101",
        "10001",
        "010110"
    })
    @DisplayName("Happy paths for method \"matchesSameStartAndEndChar\"")
    void matchesSameStartAndEndChar_HappyPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesSameStartAndEndChar(str);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - \"{0}\" matches strings with a length between 1 and 3 characters")
    @ValueSource(strings = {
        "0",
        "10",
        "001",
        "110"
    })
    @DisplayName("Happy paths for method \"matchesLengthBetweenOneAndThree\"")
    void matchesLengthBetweenOneAndThree_HappyPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesLengthBetweenOneAndThree(str);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - \"{0}\" doesn't match at least 3 characters with the 3rd one being '0'")
    @ValueSource(strings = {
        "",
        "0",
        "00",
        "011",
        "1010",
        "10110"
    })
    @DisplayName("Fail paths for method \"matchesAtLeastThreeCharsThirdIsZero\"")
    void matchesAtLeastThreeCharsThirdIsZero_FailPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesAtLeastThreeCharsThirdIsZero(str);

        // Assert
        Assertions.assertFalse(actual);
    }

    @ParameterizedTest(name = "Test {index} - \"{0}\" doesn't match strings with the same start and end ('0' or '1')")
    @ValueSource(strings = {
        "100",
        "001",
        "00001",
        "010111"
    })
    @DisplayName("Fail paths for method \"matchesSameStartAndEndChar\"")
    void matchesSameStartAndEndChar_FailPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesSameStartAndEndChar(str);

        // Assert
        Assertions.assertFalse(actual);
    }

    @ParameterizedTest(name = "Test {index} - \"{0}\" doesn't match strings with a length between 1 and 3 characters")
    @ValueSource(strings = {
        "",
        "1101",
        "00010",
        "110111"
    })
    @DisplayName("Fail paths for method \"matchesLengthBetweenOneAndThree\"")
    void matchesLengthBetweenOneAndThree_FailPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesLengthBetweenOneAndThree(str);

        // Assert
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Test when input string for \"matchesAtLeastThreeCharsThirdIsZero\" is null")
    void matchesAtLeastThreeCharsThirdIsZero_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> RegexPatternMatcher.matchesAtLeastThreeCharsThirdIsZero(null)
        );
    }

    @Test
    @DisplayName("Test when input string for \"matchesSameStartAndEndChar\" is null")
    void matchesSameStartAndEndChar_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> RegexPatternMatcher.matchesSameStartAndEndChar(null)
        );
    }

    @Test
    @DisplayName("Test when input string for \"matchesLengthBetweenOneAndThree\" is null")
    void matchesLengthBetweenOneAndThree_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> RegexPatternMatcher.matchesLengthBetweenOneAndThree(null)
        );
    }
}
