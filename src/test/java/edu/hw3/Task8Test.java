package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 3, Task 8
 */
class Task8Test {
    @Test
    @DisplayName("Test BackwardIterator with Integer collection")
    void backwardIterator_TestWithIntegerCollection() {
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
    @DisplayName("Test BackwardIterator with empty collection")
    void backwardIterator_TestHasNextWithEmptyCollection() {
        // Arrange
        BackwardIterator<Integer> iterator = new BackwardIterator<>(Collections.emptyList());

        // Act
        boolean actual = iterator.hasNext();

        // Assert
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Test BackwardIterator with empty collection")
    void backwardIterator_TestNextWithEmptyCollection() {
        // Arrange
        var expectedType = NoSuchElementException.class;
        BackwardIterator<Integer> iterator = new BackwardIterator<>(Collections.emptyList());

        // Act & Assert
        Assertions.assertThrows(expectedType, iterator::next);
    }
}
