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
public class Task3Test {
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
        int[] a1 = stringToArray(a1Str);
        int[] a2 = stringToArray(a2Str);
        boolean actual = Task3.isNestable(a1, a2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test when first array is null")
    void isNestable_FirstArrayIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> Task3.isNestable(null, new int[] {1, 2}));
    }

    @Test
    @DisplayName("Test when second array is null")
    void isNestable_SecondArrayIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> Task3.isNestable(new int[] {1, 2}, null));
    }

    @Test
    @DisplayName("Test when both arrays are null")
    void isNestable_BothArraysAreNull() {
        Assertions.assertThrows(NullPointerException.class, () -> Task3.isNestable(null, null));
    }

    //region Utilities

    private static int[] stringToArray(String str) {
        try {
            return Arrays.stream(str.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        } catch (Exception e) {
            return new int[] {};
        }
    }

    //endregion
}
