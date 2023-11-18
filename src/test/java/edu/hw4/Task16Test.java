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
 * Tests for Homework 4, Task 16
 */
class Task16Test {
    private AutoCloseable closeable;

    @Mock
    Animal a1, a2, a3, a4, a5, a6, a7, a8, a9;

    @BeforeEach
    void open() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Sort a list of animals by type, sex, and name")
    void task16_SortAnimalsByTypeSexName() {
        // Arrange
        when(a1.type()).thenReturn(Animal.Type.DOG);
        when(a2.type()).thenReturn(Animal.Type.FISH);
        when(a3.type()).thenReturn(Animal.Type.CAT);
        when(a4.type()).thenReturn(Animal.Type.FISH);
        when(a5.type()).thenReturn(Animal.Type.CAT);
        when(a6.type()).thenReturn(Animal.Type.DOG);
        when(a7.type()).thenReturn(Animal.Type.FISH);
        when(a8.type()).thenReturn(Animal.Type.FISH);
        when(a9.type()).thenReturn(Animal.Type.SPIDER);

        when(a1.sex()).thenReturn(Animal.Sex.M);
        when(a4.sex()).thenReturn(Animal.Sex.F);
        when(a2.sex()).thenReturn(Animal.Sex.F);
        when(a6.sex()).thenReturn(Animal.Sex.F);
        when(a3.sex()).thenReturn(Animal.Sex.M);
        when(a8.sex()).thenReturn(Animal.Sex.F);
        when(a5.sex()).thenReturn(Animal.Sex.M);
        when(a7.sex()).thenReturn(Animal.Sex.F);
        when(a9.sex()).thenReturn(Animal.Sex.F);

        when(a1.name()).thenReturn("Retriever");
        when(a2.name()).thenReturn("Goldfish");
        when(a3.name()).thenReturn("Siamese");
        when(a4.name()).thenReturn("Clownfish");
        when(a5.name()).thenReturn("Persian");
        when(a6.name()).thenReturn("Shepherd");
        when(a7.name()).thenReturn("Catfish");
        when(a8.name()).thenReturn("Tuna");
        when(a9.name()).thenReturn("Tarantula");

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9);
        List<Animal> expected = List.of(a5, a3, a1, a6, a7, a4, a2, a8, a9);

        // Act
        List<Animal> actual = AnimalHelper.task16SortAnimalsByTypeSexName(animals);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
