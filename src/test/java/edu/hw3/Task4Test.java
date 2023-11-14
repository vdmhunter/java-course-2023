package edu.hw3;

import edu.hw3.task4.IntegerToRomanConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for Homework 3, Task 4
 */
class Task4Test {
    @ParameterizedTest(name = "Test {index} - {0} to Roman numeral is: {1}")
    @CsvSource({
        "2,II",
        "12,XII",
        "16,XVI",
        "2023,MMXXIII",
        "450,CDL",
        "1,I",
        "3999,MMMCMXCIX"
    })
    @DisplayName("Happy paths")
    void integerToRoman_HappyPaths(int num, String expected) {
        // Act
        String actual = IntegerToRomanConverter.intToRoman(num);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - Invalid Input: {0}")
    @ValueSource(strings = {
        "-1",
        "0",
        "4000"
    })
    @DisplayName("Fail paths")
    void integerToRoman_FailPaths(int num) {
        // Arrange
        var expectedType = IllegalArgumentException.class;
        Executable executable = () -> IntegerToRomanConverter.intToRoman(num);

        // Act & Assert
        Assertions.assertThrows(expectedType, executable);
    }
}
