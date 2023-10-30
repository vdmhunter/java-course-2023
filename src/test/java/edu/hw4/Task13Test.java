package edu.hw4;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.mockito.Mockito.when;

/**
 * Tests for Homework 4, Task 13
 */
public class Task13Test {
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
    @DisplayName("Find animals with names consisting of more than two words")
    void task13_FindAnimalsWithNamesConsistingOfMoreThanTwoWords() {
        // Arrange
        when(a1.name()).thenReturn("Falcon");
        when(a2.name()).thenReturn("Polar Bear");
        when(a3.name()).thenReturn("Brazilian Wandering Spider");

        List<Animal> animals = List.of(a1, a2, a3);
        List<Animal> expected = List.of(a3);

        // Act
        List<Animal> actual = AnimalHelper.task13FindAnimalsWithNamesConsistingOfMoreThanTwoWords(animals);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
