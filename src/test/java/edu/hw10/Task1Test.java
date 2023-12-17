package edu.hw10;

import edu.hw10.task1.RandomObjectGenerator;
import edu.hw10.task1.classes.AnimalPOJO;
import edu.hw10.task1.classes.AnimalRecord;
import edu.hw10.task1.classes.AnimalWithFactoryMethod;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Tests for Homework 10, Task 1
 */
class Task1Test {
    @Test
    @DisplayName("AnimalPOJO annotations test")
    void animalPOJO_TestAnnotations()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        var generator = new RandomObjectGenerator();

        // Act
        AnimalPOJO animal = generator.nextObject(AnimalPOJO.class);

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(animal.getName()),
            () -> Assertions.assertTrue(animal.getPawsCount() >= 2),
            () -> Assertions.assertTrue(animal.getPawsCount() <= 40)
        );
    }

    @Test
    @DisplayName("AnimalRecord annotations test")
    void animalRecord_TestAnnotations()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        var generator = new RandomObjectGenerator();

        // Act
        AnimalRecord animal = generator.nextObject(AnimalRecord.class);

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(animal.name()),
            () -> Assertions.assertTrue(animal.pawsCount() >= 2)
        );
    }

    @Test
    @DisplayName("AnimalWithFactoryMethod annotations test")
    void animalWithFactoryMethod_TestAnnotations()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        var generator = new RandomObjectGenerator();

        // Act
        AnimalWithFactoryMethod animal = generator.nextObject(AnimalWithFactoryMethod.class, "create");

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(animal.getName()),
            () -> Assertions.assertTrue(animal.getPawsCount() <= 40)
        );
    }

    @Test
    @DisplayName("Creating AnimalWithFactoryMethod with 'find' method throws InstantiationException")
    void animalWithFactoryMethod_TestThrowsInstantiationException() {
        // Arrange
        var expectedType = InstantiationException.class;
        var generator = new RandomObjectGenerator();

        // Act
        Executable executable = () -> generator.nextObject(AnimalWithFactoryMethod.class, "find");

        // Assert
        Assertions.assertThrows(expectedType, executable);
    }
}
