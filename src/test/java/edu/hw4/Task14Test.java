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
 * Tests for Homework 4, Task 14
 */
class Task14Test {
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
    @DisplayName("Check if there is a dog taller than a specified height")
    void task14_HasDogTallerThanK() {
        // Arrange
        when(a1.type()).thenReturn(Animal.Type.BIRD);
        when(a2.type()).thenReturn(Animal.Type.SPIDER);
        when(a3.type()).thenReturn(Animal.Type.DOG);

        when(a1.height()).thenReturn(120);
        when(a2.height()).thenReturn(10);
        when(a3.height()).thenReturn(141);

        int k = 100;
        List<Animal> animals = List.of(a1, a2, a3);

        // Act
        boolean actual = AnimalHelper.task14HasDogTallerThanK(animals, k);

        // Assert
        Assertions.assertTrue(actual);
    }
}
