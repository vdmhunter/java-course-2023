package edu.hw3;

import edu.hw3.Task7.NullSafeComparator;
import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Tests for Homework 3, Task 7
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task7Test {
    @Test()
    @Order(1)
    @DisplayName("Test adding null key to TreeMap with NullSafeComparator")
    void nullSafeComparator_containsNullKey() {
        // Arrange
        TreeMap<String, String> tree = new TreeMap<>(new NullSafeComparator<>(String::compareTo));

        // Act
        tree.put(null, "test");

        // Assert
        Assertions.assertTrue(tree.containsKey(null));
    }

    @Test
    @Order(2)
    @DisplayName("Compare with both null keys")
    void nullSafeComparator_testCompareBothNull() {
        // Arrange
        NullSafeComparator<String> comparator = new NullSafeComparator<>(String::compareTo);

        // Act
        @SuppressWarnings("EqualsWithItself")
        int actual = comparator.compare(null, null);

        // Assert
        Assertions.assertEquals(0, actual);
    }

    @Test
    @Order(3)
    @DisplayName("Compare with first key null")
    void nullSafeComparator_testCompareFirstNull() {
        // Arrange
        NullSafeComparator<String> comparator = new NullSafeComparator<>(String::compareTo);

        // Act
        int result = comparator.compare(null, "test");

        // Assert
        Assertions.assertEquals(-1, result);
    }

    @Test
    @Order(4)
    @DisplayName("Compare with second key null")
    void nullSafeComparator_testCompareSecondNull() {
        // Arrange
        NullSafeComparator<String> comparator = new NullSafeComparator<>(String::compareTo);

        // Act
        int result = comparator.compare("test", null);

        // Assert
        Assertions.assertEquals(1, result);
    }

    @Test
    @Order(5)
    @DisplayName("Compare with both keys not null")
    void nullSafeComparator_testCompareBothNotNull() {
        // Arrange
        NullSafeComparator<String> comparator = new NullSafeComparator<>(String::compareTo);

        // Act
        int result = comparator.compare("apple", "banana");

        // Assert
        Assertions.assertEquals(-1, result);
    }

    @Test
    @Order(6)
    @DisplayName("Compare with both keys not null and equal")
    void nullSafeComparator_testCompareBothNotNullEqual() {
        // Arrange
        NullSafeComparator<String> comparator = new NullSafeComparator<>(String::compareTo);

        // Act
        @SuppressWarnings("EqualsWithItself")
        int result = comparator.compare("apple", "apple");

        // Assert
        Assertions.assertEquals(0, result);
    }
}
