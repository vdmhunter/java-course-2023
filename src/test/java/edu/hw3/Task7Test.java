package edu.hw3;

import edu.hw3.task7.NullSafeComparator;
import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 3, Task 7
 */
class Task7Test {
    @Test()
    @DisplayName("Test adding null key to TreeMap with NullSafeComparator")
    void nullSafeComparator_containsNullKey() {
        // Arrange
        TreeMap<String, String> tree = new TreeMap<>(new NullSafeComparator<>(String::compareTo));

        // Act
        tree.put(null, "test");
        boolean actual = tree.containsKey(null);

        // Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Compare with both null keys")
    void nullSafeComparator_testCompareBothNull() {
        // Arrange
        int expected = 0;
        NullSafeComparator<String> comparator = new NullSafeComparator<>(String::compareTo);

        // Act
        @SuppressWarnings("EqualsWithItself")
        int actual = comparator.compare(null, null);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Compare with first key null")
    void nullSafeComparator_testCompareFirstNull() {
        // Arrange
        int expected = -1;
        NullSafeComparator<String> comparator = new NullSafeComparator<>(String::compareTo);

        // Act
        int actual = comparator.compare(null, "test");

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Compare with second key null")
    void nullSafeComparator_testCompareSecondNull() {
        // Arrange
        int expected = 1;
        NullSafeComparator<String> comparator = new NullSafeComparator<>(String::compareTo);

        // Act
        int actual = comparator.compare("test", null);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Compare with both keys not null")
    void nullSafeComparator_testCompareBothNotNull() {
        // Arrange
        int expected = -1;
        NullSafeComparator<String> comparator = new NullSafeComparator<>(String::compareTo);

        // Act
        int actual = comparator.compare("apple", "banana");

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Compare with both keys not null and equal")
    void nullSafeComparator_testCompareBothNotNullEqual() {
        // Arrange
        int expected = 0;
        NullSafeComparator<String> comparator = new NullSafeComparator<>(String::compareTo);

        // Act
        @SuppressWarnings("EqualsWithItself")
        int actual = comparator.compare("apple", "apple");

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
