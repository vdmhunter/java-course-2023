package edu.hw4;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.mockito.Mockito.when;

/**
 * Tests for Homework 4, Task 7
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Task07Test {
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
    @Order(1)
    @DisplayName("Find the k-th the oldest animal in the list. K is within bounds")
    void task7_FindFirstOldestAnimalWithinBounds() {
        // Arrange
        when(a1.age()).thenReturn(1);
        when(a2.age()).thenReturn(2);
        when(a3.age()).thenReturn(3);

        int k = 1;
        List<Animal> animals = List.of(a1, a2, a3);
        Animal expected = a1;

        // Act
        Animal actual = AnimalHelper.task7FindKthOldestAnimal(animals, k);

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(actual),
            () -> Assertions.assertEquals(expected, actual)
        );
    }

    @Test
    @Order(2)
    @DisplayName("Return null when K is out of bounds")
    void task7_FindKthOldestAnimalReturnNullWhenKIsOutOfBounds() {
        // Arrange
        when(a1.age()).thenReturn(1);
        when(a2.age()).thenReturn(2);
        when(a3.age()).thenReturn(3);

        int k = 4;
        List<Animal> animals = List.of(a1, a2, a3);

        // Act
        Animal actual = AnimalHelper.task7FindKthOldestAnimal(animals, k);

        // Assert
        Assertions.assertNull(actual);
    }
}
