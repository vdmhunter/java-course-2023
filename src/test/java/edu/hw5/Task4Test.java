package edu.hw5;

import edu.hw5.task4.PasswordValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Homework 5, Task 4
 */
class Task4Test {
    @ParameterizedTest(name = "Test {index} - Password \"{0}\" contains required characters: {1}")
    @CsvSource({
        "~!@#$%^&*|",
        "aaa~bbb",
        "secret!",
        "P@ssword",
        "strong#",
        "pa$sword",
        "per%cent",
        "^cute",
        "&and",
        "star*",
        "wa|l"
    })
    @DisplayName("Happy paths")
    void containsRequiredCharacters_HappyPaths(String password) {
        // Act
        boolean actual = PasswordValidator.containsRequiredCharacters(password);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - Password \"{0}\" contains required characters: {1}")
    @CsvSource({
        "''",
        "ab",
        "secret",
        "Password",
        "strong",
        "password",
        "percent",
        "cute",
        "and",
        "star",
        "wall"
    })
    @DisplayName("Fail paths")
    void containsRequiredCharacters_FailPaths(String password) {
        // Act
        boolean actual = PasswordValidator.containsRequiredCharacters(password);

        // Assert
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Test when input string is null")
    void containsRequiredCharacters_InputStringIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> PasswordValidator.containsRequiredCharacters(null));
    }
}
