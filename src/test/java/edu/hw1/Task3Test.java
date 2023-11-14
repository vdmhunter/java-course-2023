package edu.hw1;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Homework 1, Task 3
 */
class Task3Test {
    @ParameterizedTest(name = "Test {index} - Array [{0}] nested in array [{1}] should return ''{2}''")
    @CsvSource({
        "'1,2,3,4', '0,6', true",
        "'3,1', '4,0', true",
        "'9,9,8', '8,9', false",
        "'1,2,3,4', '2,3', false",
        "'', '2,3', false",
        "'1,3,5', '', false",
        "'', '', false"
    })
    @DisplayName("Happy Paths")
    void isNestable_HappyPaths(String a1Str, String a2Str, boolean expected) {
        // Arrange
        int[] a1 = stringToArray(a1Str);
        int[] a2 = stringToArray(a2Str);

        // Act
        boolean actual = Task3.isNestable(a1, a2);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test when first array is null")
    void isNestable_FirstArrayIsNull() {
        // Arrange
        var expectedType = NullPointerException.class;

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> Task3.isNestable(null, new int[] {1, 2}));
    }

    @Test
    @DisplayName("Test when second array is null")
    void isNestable_SecondArrayIsNull() {
        // Arrange
        var expectedType = NullPointerException.class;

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> Task3.isNestable(new int[] {1, 2}, null));
    }

    @Test
    @DisplayName("Test when both arrays are null")
    void isNestable_BothArraysAreNull() {
        // Arrange
        var expectedType = NullPointerException.class;

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> Task3.isNestable(null, null));
    }

    /**
     * Converts a comma-separated string of integers into an array of integers.
     * If the conversion encounters any parsing errors or exceptions, an empty array is returned.
     *
     * @param str the input string containing comma-separated integers.
     * @return an array of integers parsed from the input string, or an empty array if parsing fails.
     * @throws NullPointerException if the input string is {@code null}.
     */
    private static int[] stringToArray(String str) {
        try {
            return Arrays.stream(str.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        } catch (Exception e) {
            return new int[] {};
        }
    }
}
