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
 * Tests for Homework 4, Task 6
 */
public class Task06Test {
    private AutoCloseable closeable;

    @Mock
    Animal a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;

    @BeforeEach
    void open() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Find the heaviest animal for each animal type")
    void task6_FindHeaviestAnimalByType() {
        // Arrange
        when(a1.type()).thenReturn(Animal.Type.BIRD);
        when(a2.type()).thenReturn(Animal.Type.SPIDER);
        when(a3.type()).thenReturn(Animal.Type.CAT);
        when(a4.type()).thenReturn(Animal.Type.DOG);
        when(a5.type()).thenReturn(Animal.Type.BIRD);
        when(a6.type()).thenReturn(Animal.Type.FISH);
        when(a7.type()).thenReturn(Animal.Type.SPIDER);
        when(a8.type()).thenReturn(Animal.Type.CAT);
        when(a9.type()).thenReturn(Animal.Type.CAT);
        when(a10.type()).thenReturn(Animal.Type.DOG);

        when(a1.weight()).thenReturn(7);
        when(a2.weight()).thenReturn(12);
        when(a3.weight()).thenReturn(16);
        when(a4.weight()).thenReturn(6);
        when(a5.weight()).thenReturn(23);
        when(a6.weight()).thenReturn(1);
        when(a7.weight()).thenReturn(45);
        when(a8.weight()).thenReturn(9);
        when(a9.weight()).thenReturn(22);
        when(a10.weight()).thenReturn(18);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);

        Map<Animal.Type, Animal> expected = Map.of(
            Animal.Type.CAT, a9,
            Animal.Type.DOG, a10,
            Animal.Type.BIRD, a5,
            Animal.Type.FISH, a6,
            Animal.Type.SPIDER, a7
        );

        // Act
        Map<Animal.Type, Animal> actual = AnimalHelper.task6FindHeaviestAnimalByType(animals);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
