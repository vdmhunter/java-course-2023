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
 * Tests for Homework 4, Task 18
 */
public class Task18Test {
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
    @DisplayName("Find the heaviest fish among multiple lists of animals")
    void task18_FindHeaviestFish() {
        // Arrange
        when(a5.type()).thenReturn(Animal.Type.FISH);
        when(a1.type()).thenReturn(Animal.Type.DOG);
        when(a4.type()).thenReturn(Animal.Type.FISH);
        when(a2.type()).thenReturn(Animal.Type.FISH);
        when(a3.type()).thenReturn(Animal.Type.CAT);
        when(a6.type()).thenReturn(Animal.Type.FISH);

        when(a5.weight()).thenReturn(4);
        when(a1.weight()).thenReturn(6);
        when(a4.weight()).thenReturn(5);
        when(a2.weight()).thenReturn(1);
        when(a3.weight()).thenReturn(2);
        when(a6.weight()).thenReturn(3);

        List<List<Animal>> listOfAnimalLists = List.of(
            List.of(a1, a2),
            List.of(a3, a4),
            List.of(a5, a6)
        );
        Animal expected = a4;

        // Act
        Animal actual = AnimalHelper.task18FindHeaviestFish(listOfAnimalLists);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
