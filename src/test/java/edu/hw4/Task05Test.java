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
 * Tests for Homework 4, Task 5
 */
public class Task05Test {
    private AutoCloseable closeable;

    @Mock
    Animal a1, a2, a3, a4;

    @BeforeEach
    void open() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Compare the genders (male vs. female). Count of male less then count of female")
    void task5_CompareGendersMaleLessToFemale() {
        // Arrange
        when(a1.sex()).thenReturn(Animal.Sex.M);
        when(a2.sex()).thenReturn(Animal.Sex.F);
        when(a3.sex()).thenReturn(Animal.Sex.F);

        List<Animal> animals = List.of(a1, a2, a3);
        Animal.Sex expected = Animal.Sex.F;

        // Act
        Animal.Sex actual = AnimalHelper.task5CompareGenders(animals);

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(actual),
            () -> Assertions.assertEquals(expected, actual)
        );
    }

    @Test
    @DisplayName("Compare the genders (male vs. female). Count of male greater then count of female")
    void task5_CompareGendersMaleGreaterFemale() {
        // Arrange
        when(a1.sex()).thenReturn(Animal.Sex.M);
        when(a2.sex()).thenReturn(Animal.Sex.M);
        when(a3.sex()).thenReturn(Animal.Sex.F);

        List<Animal> animals = List.of(a1, a2, a3);
        Animal.Sex expected = Animal.Sex.M;

        // Act
        Animal.Sex actual = AnimalHelper.task5CompareGenders(animals);

        // Assert
        Assertions.assertAll(
            () -> Assertions.assertNotNull(actual),
            () -> Assertions.assertEquals(expected, actual)
        );
    }

    @Test
    @DisplayName("Compare the genders (male vs. female). Count of male equal to count of female")
    void task5_CompareGendersMaleEqualToFemale() {
        // Arrange
        when(a1.sex()).thenReturn(Animal.Sex.M);
        when(a2.sex()).thenReturn(Animal.Sex.F);
        when(a3.sex()).thenReturn(Animal.Sex.F);
        when(a4.sex()).thenReturn(Animal.Sex.M);

        List<Animal> animals = List.of(a1, a2, a3, a4);

        // Act
        Animal.Sex actual = AnimalHelper.task5CompareGenders(animals);

        // Assert
        Assertions.assertNull(actual);
    }
}
