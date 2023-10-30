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
 * Tests for Homework 4, Task 10
 */
public class Task10Test {
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
    @DisplayName("Find animals whose age does not match the number of paws they have")
    void task10_FindAnimalsWithAgeNotMatchingPaws() {
        // Arrange
        when(a1.paws()).thenReturn(2);
        when(a2.paws()).thenReturn(4);
        when(a3.paws()).thenReturn(4);
        when(a4.paws()).thenReturn(0);
        when(a5.paws()).thenReturn(8);

        when(a1.age()).thenReturn(2);
        when(a2.age()).thenReturn(4);
        when(a3.age()).thenReturn(4);
        when(a4.age()).thenReturn(1);
        when(a5.age()).thenReturn(7);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5);
        List<Animal> expected = List.of(a4, a5);

        // Act
        List<Animal> actual = AnimalHelper.task10FindAnimalsWithAgeNotMatchingPaws(animals);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
