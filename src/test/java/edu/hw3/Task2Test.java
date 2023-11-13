package edu.hw3;

import edu.hw3.task2.BracketClusterizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for Homework 3, Task 2
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task2Test {
    @ParameterizedTest(name = "Test {index} - Happy path: Bracket clustering for \"{0}\"")
    @Order(1)
    @MethodSource("provideBracketsForHappyPaths")
    @DisplayName("Happy paths")
    void clusterize_HappyPaths(String str, ArrayList<String> expected) {
        // Act
        List<String> actual = BracketClusterizer.clusterize(str);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - FailPath: Bracket clustering for \"{0}\"")
    @Order(2)
    @MethodSource("provideBracketsForFailPaths")
    @DisplayName("Fail paths")
    void knightBoardCapture_FailPaths(String str) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BracketClusterizer.clusterize(str));
    }

    @Test
    @Order(3)
    @DisplayName("Test when input string is null")
    void clusterize_InputStringIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> BracketClusterizer.clusterize(null));
    }

    @Test
    @Order(4)
    @DisplayName("Test when input string is empty")
    void clusterize_InputStringIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BracketClusterizer.clusterize(""));
    }

    private static @NotNull Stream<Arguments> provideBracketsForHappyPaths() {
        return Stream.of(
            Arguments.of("()()()", new ArrayList<>(Arrays.asList("()", "()", "()"))),
            Arguments.of("((()))", new ArrayList<>(List.of("((()))"))),
            Arguments.of(
                "((()))(())()()(()())",
                new ArrayList<>(Arrays.asList("((()))", "(())", "()", "()", "(()())"))
            ),
            Arguments.of("((())())(()(()()))", new ArrayList<>(Arrays.asList("((())())", "(()(()()))")))
        );
    }

    private static @NotNull Stream<Arguments> provideBracketsForFailPaths() {
        return Stream.of(
            Arguments.of("()()("),
            Arguments.of("(()))"),
            Arguments.of("(()))(())())(()())"),
            Arguments.of("((()())(()(()())")
        );
    }
}
