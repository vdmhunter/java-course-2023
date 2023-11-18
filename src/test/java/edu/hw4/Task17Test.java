package edu.hw4;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.mockito.Mockito.when;

/**
 * Tests for Homework 4, Task 17
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Task17Test {
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
    @Order(1)
    @DisplayName("Check if spiders bite more often than dogs (true)")
    void task17_SpidersBiteMoreOften() {
        // Arrange
        when(a1.type()).thenReturn(Animal.Type.SPIDER);
        when(a2.type()).thenReturn(Animal.Type.SPIDER);
        when(a3.type()).thenReturn(Animal.Type.SPIDER);
        when(a4.type()).thenReturn(Animal.Type.SPIDER);
        when(a5.type()).thenReturn(Animal.Type.DOG);
        when(a6.type()).thenReturn(Animal.Type.DOG);
        when(a7.type()).thenReturn(Animal.Type.DOG);

        when(a1.bites()).thenReturn(true);
        when(a2.bites()).thenReturn(true);
        when(a3.bites()).thenReturn(false);
        when(a4.bites()).thenReturn(null);
        when(a5.bites()).thenReturn(true);
        when(a6.bites()).thenReturn(false);
        when(a7.bites()).thenReturn(null);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6, a7);

        // Act
        boolean actual = AnimalHelper.task17DoSpidersBiteMoreOften(animals);

        // Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @Order(2)
    @DisplayName("Check if spiders bite more often than dogs (false)")
    void task17_SpidersNotBiteMoreOften() {
        // Arrange
        when(a1.type()).thenReturn(Animal.Type.DOG);
        when(a2.type()).thenReturn(Animal.Type.DOG);
        when(a3.type()).thenReturn(Animal.Type.CAT);
        when(a4.type()).thenReturn(Animal.Type.CAT);
        when(a5.type()).thenReturn(Animal.Type.DOG);
        when(a6.type()).thenReturn(Animal.Type.CAT);
        when(a7.type()).thenReturn(Animal.Type.CAT);

        when(a1.bites()).thenReturn(true);
        when(a2.bites()).thenReturn(false);
        when(a3.bites()).thenReturn(true);
        when(a4.bites()).thenReturn(false);
        when(a5.bites()).thenReturn(null);
        when(a6.bites()).thenReturn(null);
        when(a7.bites()).thenReturn(null);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6, a7);

        // Act
        boolean actual = AnimalHelper.task17DoSpidersBiteMoreOften(animals);

        // Assert
        Assertions.assertFalse(actual);
    }
}
