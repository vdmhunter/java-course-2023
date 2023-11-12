package edu.hw5;

import edu.hw5.task6.SubsequenceChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Homework 5, Task 6
 */
class Task6Test {
    @ParameterizedTest(name = "Test {index} - \"{0}\" is a subsequence of \"{1}\"")
    @SuppressWarnings("SpellCheckingInspection")
    @CsvSource({
        "abc,achfdbaabgabyaacg",
        "xyz,dfxasldayzgsgsdgi",
        "zzz,zozzfdbaabgbcaabg"
    })
    @DisplayName("Happy paths")
    void isSubsequence_HappyPaths(String s, String t) {
        // Act
        boolean actual = SubsequenceChecker.isSubsequence(s, t);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - \"{0}\" is not a subsequence of \"{1}\"")
    @SuppressWarnings("SpellCheckingInspection")
    @CsvSource({
        "abc,achfdyaacgapyaacg",
        "xyz,yfxaslda0zgsgsdgi",
        "zzz,zozlfdbaabgbcaabg"
    })
    @DisplayName("Fail paths")
    void isSubsequence_FailPaths(String s, String t) {
        // Act
        boolean actual = SubsequenceChecker.isSubsequence(s, t);

        // Assert
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Test when input string 's' is null")
    void isSubsequence_InputStringSIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> SubsequenceChecker.isSubsequence(null, "t"));
    }

    @Test
    @DisplayName("Test when input string 't' is null")
    void isSubsequence_InputStringTIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> SubsequenceChecker.isSubsequence("s", null));
    }

    @Test
    @DisplayName("Test when both input strings 's' and 't' are null")
    void isSubsequence_InputStringsSAndTIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> SubsequenceChecker.isSubsequence(null, null));
    }
}
