package edu.hw4;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.mockito.Mockito.when;

/**
 * Tests for Homework 4, Task 17
 */
public class Task17Test {
    private AutoCloseable closeable;

    @Mock
    Animal a1, a2, a3, a4, a5, a6, a7;

    @BeforeEach
    void open() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Check if spiders bite more often than dogs")
    void task17_DoSpidersBiteMoreOften() {
        // Arrange
        when(a1.type()).thenReturn(Animal.Type.SPIDER);
        when(a2.type()).thenReturn(Animal.Type.SPIDER);
        when(a3.type()).thenReturn(Animal.Type.SPIDER);
        when(a4.type()).thenReturn(Animal.Type.SPIDER);
        when(a5.type()).thenReturn(Animal.Type.DOG);
        when(a6.type()).thenReturn(Animal.Type.DOG);
        when(a7.type()).thenReturn(Animal.Type.DOG);

        when(a1.bites()).thenReturn(true);
        when(a2.bites()).thenReturn(false);
        when(a3.bites()).thenReturn(false);
        when(a4.bites()).thenReturn(true);
        when(a5.bites()).thenReturn(true);
        when(a6.bites()).thenReturn(false);
        when(a7.bites()).thenReturn(false);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6);

        // Act
        boolean actual = AnimalHelper.task17DoSpidersBiteMoreOften(animals);

        // Assert
        Assertions.assertTrue(actual);
    }
}
