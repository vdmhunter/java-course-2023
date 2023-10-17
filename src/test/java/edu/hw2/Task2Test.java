package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for Homework 2, Task 2
 */
public class Task2Test {
    // Happy paths
    @ParameterizedTest(name = "Test {index} - Check area of shape")
    @MethodSource("rectangles")
    @DisplayName("Modified test from task description for verifying the area of shapes")
    void rectangle_ModifiedTaskTest(Rectangle rect) {
        // Arrange
        Rectangle result = rect
            .newRectangleWithWidth(20)
            .newRectangleWithHeight(10);

        //Act
        int area = result.area();

        //Assert
        Assertions.assertEquals(200, area);
    }

    @Test
    @DisplayName("Test the length and width of a new rectangle")
    void rectangle_NewRectangleWithDimensions() {
        // Arrange
        var rectangle = new Rectangle(3, 8);

        //Act
        var width = rectangle.getWidth();
        var height = rectangle.getHeight();

        //Assert
        Assertions.assertAll(
            () -> Assertions.assertEquals(3, width),
            () -> Assertions.assertEquals(8, height)
        );
    }

    @Test
    @DisplayName("Test the length and width of a new square")
    void square_NewSquareWithDimensions() {
        // Arrange
        var square = new Square(8);

        //Act
        var width = square.getWidth();
        var height = square.getHeight();

        //Assert
        Assertions.assertAll(
            () -> Assertions.assertEquals(8, width),
            () -> Assertions.assertEquals(8, height)
        );
    }

    @Test
    @DisplayName("Test creating a new square with a specific size")
    void square_NewSquareWithSize() {
        // Arrange
        var square = new Square().newSquareWithSize(8);

        //Act
        var width = square.getWidth();
        var height = square.getHeight();

        //Assert
        Assertions.assertAll(
            () -> Assertions.assertEquals(8, width),
            () -> Assertions.assertEquals(8, height)
        );
    }

    // Fail paths
    @ParameterizedTest(name = "Test {index} - Width: {0}, Height: {1}")
    @CsvSource({
        "-1, 5",
        "12, -3",
        "-9, -15",
    })
    @DisplayName("Test Rectangle constructor with invalid dimensions")
    void rectangle_ConstructorWithInvalidDimensions(int width, int height) {
        // Arrange
        Executable executable = () -> new Rectangle(width, height);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    @DisplayName("Test creating a new rectangle with illegal width")
    void rectangle_NewRectangleWithIllegalWidth() {
        // Arrange
        Executable executable = () -> new Rectangle().newRectangleWithWidth(-1);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    @DisplayName("Test creating a new rectangle with illegal height")
    void rectangle_NewRectangleWithIllegalHeight() {
        // Arrange
        Executable executable = () -> new Rectangle().newRectangleWithHeight(-1);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    @DisplayName("Test Square constructor with invalid size")
    void square_ConstructorWithInvalidSize() {
        // Arrange
        Executable executable = () -> new Square(-1);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    @DisplayName("Test creating a new square with illegal size")
    void square_NewSquareWithIllegalSize() {
        // Arrange
        Executable executable = () -> new Square().newSquareWithSize(-1);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }

    private static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }
}
