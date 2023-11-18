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
 * Tests for Homework 4, Task 15
 */
class Task15Test {
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
    @DisplayName("Calculate the sum of weights for each animal type within a specified age range")
    void task15_SumOfWeightsByTypeAndAgeRange() {
        // Arrange
        when(a1.type()).thenReturn(Animal.Type.BIRD);
        when(a2.type()).thenReturn(Animal.Type.CAT);
        when(a3.type()).thenReturn(Animal.Type.FISH);
        when(a4.type()).thenReturn(Animal.Type.BIRD);
        when(a5.type()).thenReturn(Animal.Type.DOG);

        when(a1.weight()).thenReturn(12);
        when(a2.weight()).thenReturn(33);
        when(a3.weight()).thenReturn(9);
        when(a4.weight()).thenReturn(27);
        when(a5.weight()).thenReturn(43);

        when(a1.age()).thenReturn(3);
        when(a2.age()).thenReturn(2);
        when(a3.age()).thenReturn(1);
        when(a4.age()).thenReturn(4);
        when(a5.age()).thenReturn(5);

        int k = 2;
        int l = 5;
        List<Animal> animals = List.of(a1, a2, a3, a4, a5);
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.BIRD, 39,
            Animal.Type.DOG, 43,
            Animal.Type.CAT, 33
        );

        // Act
        Map<Animal.Type, Integer> actual = AnimalHelper.task15SumOfWeightsByTypeAndAgeRange(animals, k, l);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
