package edu.hw4;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.mockito.Mockito.when;

/**
 * Tests for Homework 4, Task 11
 */
class Task11Test {
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
    @DisplayName("Find animals that can bite and are taller than 100 centimeters")
    void task11_FindAnimalsCanBiteAndTall() {
        // Arrange
        when(a1.bites()).thenReturn(true);
        when(a2.bites()).thenReturn(false);
        when(a3.bites()).thenReturn(true);

        when(a1.height()).thenReturn(123);
        when(a2.height()).thenReturn(40);
        when(a3.height()).thenReturn(100);

        List<Animal> animals = List.of(a1, a2, a3);
        List<Animal> expected = List.of(a1);

        // Act
        List<Animal> actual = AnimalHelper.task11FindAnimalsCanBiteAndTall(animals);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
