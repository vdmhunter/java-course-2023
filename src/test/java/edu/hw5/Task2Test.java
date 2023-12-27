package edu.hw5;

import edu.hw5.task2.FridayThirteen;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Tests for Homework 5, Task 2
 */
class Task2Test {
    @Test
    @DisplayName("Test findFridaysThirteen method")
    void findFridaysThirteen_Test() {
        // Arrange
        int year = 2023;

        // Act
        List<LocalDate> result = FridayThirteen.findFridaysThirteen(year);

        //Assert
        Assertions.assertAll(
            () -> Assertions.assertEquals(2, result.size()),
            () -> Assertions.assertEquals(LocalDate.of(year, 1, 13), result.getFirst()),
            () -> Assertions.assertEquals(LocalDate.of(year, 10, 13), result.get(1))
        );
    }

    @Test
    @DisplayName("Test findNextFriday13 method")
    void findNextFriday13_Test() {
        // Arrange
        LocalDate startDate = LocalDate.of(2022, 12, 14);

        // Act
        LocalDate result = FridayThirteen.findNextFriday13(startDate);

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertEquals(LocalDate.of(2023, 1, 13), result),
            () -> Assertions.assertEquals(DayOfWeek.FRIDAY, result.getDayOfWeek())
        );
    }

    @Test
    @DisplayName("Test findFridaysThirteen method with illegal year")
    void findFridaysThirteen_TestWithIllegalInputYear() {
        // Arrange
        Executable executable = () -> FridayThirteen.findFridaysThirteen(-1);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    @DisplayName("Test findNextFriday13 method with null date")
    void findNextFriday13_TestWithIllegalYear() {
        // Arrange
        Executable executable = () -> FridayThirteen.findNextFriday13(null);

        // Act & Assert
        Assertions.assertThrows(NullPointerException.class, executable);
    }
}
