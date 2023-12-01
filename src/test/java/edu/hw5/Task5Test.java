package edu.hw5;

import edu.hw5.task5.CarPlateValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Homework 5, Task 5
 */
class Task5Test {
    @ParameterizedTest(name = "Test {index} - Valid Russian Car Plate \"{0}\"")
    @CsvSource({
        "А123ВЕ777",
        "О777ОО177"
    })
    @DisplayName("Happy paths")
    void isCorrectRussianCarPlate_HappyPaths(String password) {
        // Act
        boolean actual = CarPlateValidator.isCorrectRussianCarPlate(password);

        // Assert
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest(name = "Test {index} - Invalid Russian Car Plate \"{0}\"")
    @CsvSource({
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777"
    })
    @DisplayName("Fail paths")
    void isCorrectRussianCarPlate_FailPaths(String password) {
        // Act
        boolean actual = CarPlateValidator.isCorrectRussianCarPlate(password);

        // Assert
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Test when input string is null")
    void isCorrectRussianCarPlate_InputStringIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> CarPlateValidator.isCorrectRussianCarPlate(null));
    }
}
