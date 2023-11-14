package edu.hw3;

import edu.hw3.task3.ItemCounter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for Homework 3, Task 3
 */
class Task3Test {
    @ParameterizedTest(name = "Test {index} - Generate a frequency map for {0}")
    @MethodSource("provideObjectsForHappyPaths")
    @DisplayName("Happy paths")
    void freqDict_HappyPaths(List<Object> list, Map<Object, Integer> expected) {
        // Act
        Map<Object, Integer> actual = ItemCounter.freqDict(list);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test when input list is null")
    void freqDict_InputStringIsNull() {
        // Arrange
        var expectedType = NullPointerException.class;

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> ItemCounter.freqDict(null));
    }

    @Test
    @DisplayName("Test when input list is empty")
    void freqDict_InputStringIsEmpty() {
        // Arrange
        var expectedType = IllegalArgumentException.class;
        var emptyArrayList = new ArrayList<>();

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> ItemCounter.freqDict(emptyArrayList));
    }

    private static @NotNull Stream<Arguments> provideObjectsForHappyPaths() {
        return Stream.of(
            Arguments.of(
                new ArrayList<>(Arrays.asList("a", "bb", "a", "bb")),
                new HashMap<Object, Integer>() {{
                    put("bb", 2);
                    put("a", 2);
                }}
            ),
            Arguments.of(
                new ArrayList<>(Arrays.asList("this", "and", "that", "and")),
                new HashMap<Object, Integer>() {{
                    put("that", 1);
                    put("and", 2);
                    put("this", 1);
                }}
            ),
            Arguments.of(
                new ArrayList<>(Arrays.asList("код", "код", "код", "bug")),
                new HashMap<Object, Integer>() {{
                    put("код", 3);
                    put("bug", 1);
                }}
            ),
            Arguments.of(
                new ArrayList<>(Arrays.asList(1, 1, 2, 2)),
                new HashMap<Object, Integer>() {{
                    put(1, 2);
                    put(2, 2);
                }}
            )
        );
    }
}
