package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

/**
 * Tests for Homework 4, Task 4
 */
class Task04Test {
    private AutoCloseable closeable;

    @Mock
    Animal a1, a2, a3;

    @BeforeEach
    void open() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Find the animal with the longest name in the list")
    void task4_FindAnimalWithLongestName() {
        // Arrange
        when(a1.name()).thenReturn("cat");
        when(a2.name()).thenReturn("tiger");
        when(a3.name()).thenReturn("elephant");

        List<Animal> animals = List.of(a1, a2, a3);
        Animal expected = a3;

        // Act
        Animal actual = AnimalHelper.task4FindAnimalWithLongestName(animals);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
