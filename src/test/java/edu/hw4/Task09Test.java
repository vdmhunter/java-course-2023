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
 * Tests for Homework 4, Task 9
 */
class Task09Test {
    private AutoCloseable closeable;

    @Mock
    Animal a1, a2, a3, a4, a5;

    @BeforeEach
    void open() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Calculate the sum of paws of all animals in the list")
    void task9_SumOfPaws() {
        // Arrange
        when(a1.paws()).thenReturn(2);
        when(a2.paws()).thenReturn(4);
        when(a3.paws()).thenReturn(4);
        when(a4.paws()).thenReturn(0);
        when(a5.paws()).thenReturn(8);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5);
        int expected = 18;

        // Act
        int actual = AnimalHelper.task9SumOfPaws(animals);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
