package edu.hw4;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

/**
 * Tests for Homework 4, Task 3
 */
class Task03Test {
    private AutoCloseable closeable;

    @Mock
    Animal a1, a2, a3, a4, a5, a6;

    @BeforeEach
    void open() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Count the number of animals for each animal type")
    void task3_CountAnimalsByType() {
        // Arrange
        when(a1.type()).thenReturn(Animal.Type.DOG);
        when(a2.type()).thenReturn(Animal.Type.SPIDER);
        when(a3.type()).thenReturn(Animal.Type.DOG);
        when(a4.type()).thenReturn(Animal.Type.BIRD);
        when(a5.type()).thenReturn(Animal.Type.FISH);
        when(a6.type()).thenReturn(Animal.Type.FISH);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6);
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.BIRD, 1,
            Animal.Type.FISH, 2,
            Animal.Type.DOG, 2,
            Animal.Type.SPIDER, 1
        );

        // Act
        Map<Animal.Type, Integer> actual = AnimalHelper.task3CountAnimalsByType(animals);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
