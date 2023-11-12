package edu.hw5;

import edu.hw5.task1.ComputerClubAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for Homework 5, Task 1
 */
class Task1Test {
    @ParameterizedTest(name = "Test {index} - Average session time is {1}")
    @MethodSource("provideListsForHappyPaths")
    @DisplayName("Happy paths")
    void getAverageTime_HappyPaths(List<String> list, String expected) {
        // Act
        String actual = ComputerClubAnalytics.getAverageTime(list);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - Average session time for incorrect input is {1}")
    @MethodSource("provideListsForFailPaths")
    @DisplayName("Fail paths")
    void getAverageTime_FailPaths(List<String> list, String expected) {
        // Act
        String actual = ComputerClubAnalytics.getAverageTime(list);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test when input list is null")
    void getAverageTime_InputStringIsNull() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> ComputerClubAnalytics.getAverageTime(null)
        );
    }

    @Test
    @DisplayName("Test when input list is empty")
    void getAverageTime_InputStringIsEmpty() {
        // Arrange
        ArrayList<String> emptyList = new ArrayList<>();

        // Act & Assert
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> ComputerClubAnalytics.getAverageTime(emptyList)
        );
    }

    private static Stream<Arguments> provideListsForHappyPaths() {
        return Stream.of(
            Arguments.of(new ArrayList<String>() {{
                add("2022-03-12, 20:20 - 2022-03-12, 23:50");
                add("2022-04-01, 21:30 - 2022-04-02, 01:20");
            }}, "3h 40m"),
            Arguments.of(new ArrayList<String>() {{
                add("2023-10-01, 08:00 - 2023-10-03, 23:00");
                add("2023-10-05, 15:21 - 2023-10-05, 16:49");
                add("2023-10-06, 09:10 - 2023-10-07, 22:11");
                add("2023-10-08, 13:34 - 2023-10-09, 12:38");
            }}, "31h 8m")
        );
    }

    private static Stream<Arguments> provideListsForFailPaths() {
        return Stream.of(
            Arguments.of(new ArrayList<String>() {{
                add("");
                add("hello");
                add("2022-13-32, 25:77 - 2022-14-82, 31:66");
                add("2022-54-71, 38:98 - 2022-84-72, 51:80");
            }}, "0h 0m")
        );
    }
}
