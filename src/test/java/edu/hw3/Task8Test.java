package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Tests for Homework 3, Task 8
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task8Test {
    @Test
    @Order(1)
    @DisplayName("Test BackwardIterator with Integer collection")
    void backwardIterator_TesrWithIntegerCollection() {
        // Arrange
        BackwardIterator<Integer> iterator = new BackwardIterator<>(List.of(1, 2, 3));
        List<Integer> expected = List.of(3, 2, 1);
        List<Integer> actual = new ArrayList<>();

        // Act
        for (Integer ignored : expected) {
            if (iterator.hasNext()) {
                Integer i = iterator.next();
                actual.add(i);
            }
        }

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    @DisplayName("Test BackwardIterator with empty collection")
    void backwardIterator_TestHasNextWithEmptyCollection() {
        // Arrange
        BackwardIterator<Integer> iterator = new BackwardIterator<>(Collections.emptyList());

        // Act and Assert
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    @Order(3)
    @DisplayName("Test BackwardIterator with empty collection")
    void backwardIterator_TestNextWithEmptyCollection() {
        // Arrange
        BackwardIterator<Integer> iterator = new BackwardIterator<>(Collections.emptyList());

        // Act and Assert
        Assertions.assertThrows(NoSuchElementException.class, iterator::next);
    }
}
