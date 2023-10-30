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
 * Tests for Homework 4, Task 2
 */
public class Task02Test {
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
    @DisplayName("Sort a list of animals by weight in descending order and select the top 'k' animals")
    void task2_SortAndSelectTopByWeight() {
        // Arrange
        when(a1.weight()).thenReturn(1);
        when(a2.weight()).thenReturn(2);
        when(a3.weight()).thenReturn(3);

        int k = 2;

        List<Animal> animals = List.of(a1, a2, a3);
        List<Animal> expected = List.of(a3, a2);

        // Act
        List<Animal> actual = AnimalHelper.task2SortAndSelectTopByWeight(animals, k);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
