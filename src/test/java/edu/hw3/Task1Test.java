package edu.hw3;

import edu.hw3.task1.Encoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Homework 3, Task 1
 */
class Task1Test {
    @ParameterizedTest(name = "Test {index} - Encode \"{0}\" with Atbash should return \"{1}\"")
    @CsvSource({
        "Hello world!,Svool dliow!",
        "Any fool can write code that a computer can understand. " +
            "Good programmers write code that humans can understand. ― Martin Fowler," +
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
        "Привет мир!,Привет мир!"
    })
    @DisplayName("Happy paths")
    @SuppressWarnings("SpellCheckingInspection")
    void atbash_HappyPaths(String str, String expected) {
        // Act
        String actual = Encoder.atbash(str);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test when input string is null")
    @SuppressWarnings("SpellCheckingInspection")
    void atbash_InputStringIsNull() {
        // Arrange
        var expectedType = NullPointerException.class;

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> Encoder.atbash(null));
    }

    @Test
    @DisplayName("Test when input string is empty")
    @SuppressWarnings("SpellCheckingInspection")
    void atbash_InputStringIsEmpty() {
        // Arrange
        var expectedType = IllegalArgumentException.class;

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> Encoder.atbash(""));
    }
}
