package edu.hw1;

import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for Homework 1, Task 8
 */
class Task8Test {
    @ParameterizedTest(name = "Test {index} - Knight Board Capture Test")
    @MethodSource("provideBoardsForHappyPaths")
    @DisplayName("Happy paths")
    void knightBoardCapture_HappyPaths(int[][] board, boolean expected) {
        // Act
        boolean actual = Task8.knightBoardCapture(board);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Test {index} - Knight Board Capture Test")
    @MethodSource("provideBoardsForFailPath")
    @DisplayName("Fail paths")
    void knightBoardCapture_FailPaths(int[][] board) {
        // Arrange
        var expectedType = IllegalArgumentException.class;

        // Act & Assert
        Assertions.assertThrows(expectedType, () -> Task8.knightBoardCapture(board));
    }

    private static @NotNull Stream<Arguments> provideBoardsForHappyPaths() {
        return Stream.of(
            Arguments.of(new int[][] {
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0}
            }, true),
            Arguments.of(new int[][] {
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1}
            }, false),
            Arguments.of(new int[][] {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
            }, false)
        );
    }

    private static Stream<Arguments> provideBoardsForFailPath() {
        return Stream.of(
            Arguments.of((Object) null),
            Arguments.of((Object) new int[][] {
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0}
            }),
            Arguments.of((Object) new int[][] {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0}
            }),
            Arguments.of((Object) new int[][] {
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0}
            }),
            Arguments.of((Object) new int[][] {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 2, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 4, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
            })
        );
    }
}
