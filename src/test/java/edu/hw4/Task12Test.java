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
 * Tests for Homework 4, Task 12
 */
class Task12Test {
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
    @DisplayName("Count animals whose weight is greater than their height")
    void task12_CountAnimalsWeightGreaterThanHeight() {
        // Arrange
        when(a1.height()).thenReturn(1);
        when(a2.height()).thenReturn(2);
        when(a3.height()).thenReturn(3);

        when(a1.weight()).thenReturn(5);
        when(a2.weight()).thenReturn(4);
        when(a3.weight()).thenReturn(3);

        List<Animal> animals = List.of(a1, a2, a3);
        int expected = 2;

        // Act
        int actual = AnimalHelper.task12CountAnimalsWeightGreaterThanHeight(animals);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
