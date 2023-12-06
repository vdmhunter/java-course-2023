package edu.hw5;

import edu.hw5.task8.RegexPatternMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for Homework 5, Task 8
 */
class Task8Test {
    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - odd length")
    @ValueSource(strings = {
        "0",
        "010",
        "10010",
        "1001010"
    })
    @DisplayName("Happy paths for method \"matchesOddLength\"")
    void matchesOddLength_HappyPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesOddLength(str);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - Starts with 0 and has odd length," +
        "or Starts with 1 and has even length")
    @ValueSource(strings = {
        "0",
        "010",
        "1001",
        "10010101"
    })
    @DisplayName("Happy paths for method \"matchesStartsWithZeroOddLengthOrStartsWithOneEvenLength\"")
    void matchesStartsWithZeroOddLengthOrStartsWithOneEvenLength_HappyPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesStartsWithZeroOddLengthOrStartsWithOneEvenLength(str);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - '0' occurs in a multiple of three times")
    @ValueSource(strings = {
        "000",
        "011010",
        "10010000",
        "1001010110110"
    })
    @DisplayName("Happy paths for method \"matchesZeroMultipleOfThree\"")
    void matchesZeroMultipleOfThree_HappyPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesZeroMultipleOfThree(str);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - Does not match '11' or '111'")
    @ValueSource(strings = {
        "000",
        "1111",
        "011010",
        "10010000",
        "1001010110110"
    })
    @DisplayName("Happy paths for method \"matchesNotElevenOrOneHundredEleven\"")
    void matchesNotElevenOrOneHundredEleven_HappyPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesNotElevenOrOneHundredEleven(str);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - Every odd character is '1'")
    @ValueSource(strings = {
        "1",
        "101",
        "101010",
        "10111111"
    })
    @DisplayName("Happy paths for method \"matchesEveryOddCharacterIsOne\"")
    void matchesEveryOddCharacterIsOne_HappyPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesEveryOddCharacterIsOne(str);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - At Least two zeros and no more than one '1'")
    @ValueSource(strings = {
        "00",
        "0100",
        "00000",
        "000010000"
    })
    @DisplayName("Happy paths for method \"matchesAtLeastTwoZerosAndNoMoreThanOneOne\"")
    void matchesAtLeastTwoZerosAndNoMoreThanOneOne_HappyPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesAtLeastTwoZerosAndNoMoreThanOneOne(str);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - No consecutive '1's")
    @ValueSource(strings = {
        "0",
        "0101",
        "00000",
        "010010010"
    })
    @DisplayName("Happy paths for method \"matchesNoConsecutiveOnes\"")
    void matchesNoConsecutiveOnes_HappyPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesNoConsecutiveOnes(str);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - not odd length")
    @ValueSource(strings = {
        "01",
        "0100",
        "100110",
        "10010110"
    })
    @DisplayName("Fail paths for method \"matchesOddLength\"")
    void matchesOddLength_FailPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesOddLength(str);

        // Assert
        Assertions.assertFalse(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - Not starts with 0 and has odd length," +
        "or Starts with 1 and has even length")
    @ValueSource(strings = {
        "01",
        "0111",
        "10101",
        "101010101"
    })
    @DisplayName("Fail paths for method \"matchesStartsWithZeroOddLengthOrStartsWithOneEvenLength\"")
    void matchesStartsWithZeroOddLengthOrStartsWithOneEvenLength_FailPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesStartsWithZeroOddLengthOrStartsWithOneEvenLength(str);

        // Assert
        Assertions.assertFalse(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - '0' not occurs in a multiple of three times")
    @ValueSource(strings = {
        "0",
        "011110",
        "10010100",
        "1101011110110"
    })
    @DisplayName("Fail paths for method \"matchesZeroMultipleOfThree\"")
    void matchesZeroMultipleOfThree_FailPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesZeroMultipleOfThree(str);

        // Assert
        Assertions.assertFalse(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - Does match '11' or '111'")
    @ValueSource(strings = {
        "11",
        "111"
    })
    @DisplayName("Fail paths for method \"matchesNotElevenOrOneHundredEleven\"")
    void matchesNotElevenOrOneHundredEleven_FailPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesNotElevenOrOneHundredEleven(str);

        // Assert
        Assertions.assertFalse(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - Not every odd character is '1'")
    @ValueSource(strings = {
        "0",
        "001",
        "100000",
        "10011111"
    })
    @DisplayName("Fail paths for method \"matchesEveryOddCharacterIsOne\"")
    void matchesEveryOddCharacterIsOne_FailPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesEveryOddCharacterIsOne(str);

        // Assert
        Assertions.assertFalse(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - At Least two zeros and no more than one '1'")
    @ValueSource(strings = {
        "0",
        "0101",
        "01111",
        "111111111"
    })
    @DisplayName("Fail paths for method \"matchesAtLeastTwoZerosAndNoMoreThanOneOne\"")
    void matchesAtLeastTwoZerosAndNoMoreThanOneOne_FailPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesAtLeastTwoZerosAndNoMoreThanOneOne(str);

        // Assert
        Assertions.assertFalse(actual);
    }

    @ParameterizedTest(name = "Test {index} - String: \"{0}\" - No consecutive '1's")
    @ValueSource(strings = {
        "11",
        "0111",
        "01100",
        "0100111010"
    })
    @DisplayName("Fail paths for method \"matchesNoConsecutiveOnes\"")
    void matchesNoConsecutiveOnes_FailPaths(String str) {
        // Act
        boolean actual = RegexPatternMatcher.matchesNoConsecutiveOnes(str);

        // Assert
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Test when input string for \"matchesOddLength\" is null")
    void matchesOddLength_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> RegexPatternMatcher.matchesOddLength(null)
        );
    }

    @Test
    @DisplayName("Test when input string for \"matchesStartsWithZeroOddLengthOrStartsWithOneEvenLength\" is null")
    void matchesStartsWithZeroOddLengthOrStartsWithOneEvenLength_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> RegexPatternMatcher.matchesStartsWithZeroOddLengthOrStartsWithOneEvenLength(null)
        );
    }

    @Test
    @DisplayName("Test when input string for \"matchesZeroMultipleOfThree\" is null")
    void matchesZeroMultipleOfThree_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> RegexPatternMatcher.matchesZeroMultipleOfThree(null)
        );
    }

    @Test
    @DisplayName("Test when input string for \"matchesNotElevenOrOneHundredEleven\" is null")
    void matchesNotElevenOrOneHundredEleven_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> RegexPatternMatcher.matchesNotElevenOrOneHundredEleven(null)
        );
    }

    @Test
    @DisplayName("Test when input string for \"matchesEveryOddCharacterIsOne\" is null")
    void matchesEveryOddCharacterIsOne_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> RegexPatternMatcher.matchesEveryOddCharacterIsOne(null)
        );
    }

    @Test
    @DisplayName("Test when input string for \"matchesAtLeastTwoZerosAndNoMoreThanOneOne\" is null")
    void matchesAtLeastTwoZerosAndNoMoreThanOneOne_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> RegexPatternMatcher.matchesAtLeastTwoZerosAndNoMoreThanOneOne(null)
        );
    }

    @Test
    @DisplayName("Test when input string for \"matchesNoConsecutiveOnes\" is null")
    void matchesNoConsecutiveOnes_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> RegexPatternMatcher.matchesNoConsecutiveOnes(null)
        );
    }
}
