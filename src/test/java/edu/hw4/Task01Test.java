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
 * Tests for Homework 4, Task 1
 */
public class Task01Test {
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
    @DisplayName("Sort a list of animals by their height in ascending order")
    void task1_SortAnimalsByHeight() {
        // Arrange
        when(a1.height()).thenReturn(3);
        when(a2.height()).thenReturn(2);
        when(a3.height()).thenReturn(1);

        List<Animal> animals = List.of(a1, a2, a3);
        List<Animal> expected = List.of(a3, a2, a1);

        // Act
        List<Animal> actual = AnimalHelper.task1SortAnimalsByHeight(animals);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
