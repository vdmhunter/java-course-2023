package edu.hw4;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;

/**
 * Tests for Homework 4, Task 8
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task08Test {
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
    @Order(1)
    @DisplayName("Find the heaviest animal below a specified height. (Animal present)")
    void task8_FindHeaviestAnimalBelowHeightWithAnimal() {
        // Arrange
        when(a1.height()).thenReturn(5);
        when(a2.height()).thenReturn(12);
        when(a3.height()).thenReturn(2);
        when(a4.height()).thenReturn(43);
        when(a5.height()).thenReturn(22);

        when(a1.weight()).thenReturn(3);
        when(a2.weight()).thenReturn(18);
        when(a3.weight()).thenReturn(24);
        when(a4.weight()).thenReturn(8);
        when(a5.weight()).thenReturn(4);

        int height = 3;
        List<Animal> animals = List.of(a1, a2, a3, a4, a5);
        Animal expected = a3;

        // Act
        Optional<Animal> actual = AnimalHelper.task8FindHeaviestAnimalBelowHeight(animals, height);

        // Assert
        //noinspection OptionalGetWithoutIsPresent
        Assertions.assertAll(
            () -> Assertions.assertTrue(actual.isPresent()),
            () -> Assertions.assertEquals(expected, actual.get())
        );
    }

    @Test
    @Order(2)
    @DisplayName("Find the heaviest animal below a specified height. (No animal present)")
    void task1_FindHeaviestAnimalBelowHeightWithoutAnimal() {
        // Arrange
        when(a1.height()).thenReturn(5);
        when(a2.height()).thenReturn(12);
        when(a3.height()).thenReturn(2);
        when(a4.height()).thenReturn(43);
        when(a5.height()).thenReturn(22);

        when(a1.weight()).thenReturn(3);
        when(a2.weight()).thenReturn(18);
        when(a3.weight()).thenReturn(24);
        when(a4.weight()).thenReturn(8);
        when(a5.weight()).thenReturn(4);

        int height = 2;
        List<Animal> animals = List.of(a1, a2, a3, a4, a5);

        // Act
        Optional<Animal> actual = AnimalHelper.task8FindHeaviestAnimalBelowHeight(animals, height);

        // Assert
        Assertions.assertFalse(actual.isPresent());
    }
}
