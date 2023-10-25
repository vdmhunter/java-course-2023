package edu.hw3;

import edu.hw3.task5.Contact;
import edu.hw3.task5.ContactSorter;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for Homework 3, Task 5
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task5Test {
    @ParameterizedTest(name = "Test {index} - Sorting contacts: \"{0}\", order: {1}")
    @Order(1)
    @MethodSource("provideObjectsForHappyPaths")
    @DisplayName("Happy paths")
    void parseContacts_HappyPaths(String[] names, String order, Contact[] expected) {
        // Act
        Contact[] actual = ContactSorter.parseContacts(names, order);

        // Assert
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    @DisplayName("Comparing object to itself should return true")
    void contact_SelfReferenceShouldReturnTrue() {
        // Arrange
        Contact contact = new Contact("John Locke");

        // Act
        @SuppressWarnings("EqualsWithItself")
        boolean b = contact.equals(contact);

        // Assert
        Assertions.assertTrue(b);
    }

    @Test
    @Order(3)
    @DisplayName("Comparing object to null should return false")
    void contact_NullObjectShouldReturnFalse() {
        // Arrange
        Contact contact = new Contact("John Locke");

        // Act
        @SuppressWarnings("ConstantValue")
        boolean b = contact == null;

        // Assert
        Assertions.assertFalse(b);
    }

    @Test
    @Order(4)
    @DisplayName("Comparing to a different class should return false")
    void contact_DifferentClassShouldReturnFalse() {
        // Arrange
        Contact contact = new Contact("John Locke");
        String differentObject = "Not a Contact";

        // Act
        @SuppressWarnings("EqualsBetweenInconvertibleTypes")
        boolean b = contact.equals(differentObject);

        // Assert
        Assertions.assertFalse(b);

    }

    @Test
    @Order(5)
    @DisplayName("Comparing objects with the same fields should return true")
    void contact_SameFieldsShouldReturnTrue() {
        // Arrange
        Contact contact1 = new Contact("John Locke");
        Contact contact2 = new Contact("John Locke");

        // Act
        boolean b = contact1.equals(contact2);

        // Assert
        Assertions.assertTrue(b);
    }

    @Test
    @Order(6)
    @DisplayName("Comparing objects with different first names should return false")
    void contact_DifferentFirstNamesShouldReturnFalse() {
        // Arrange
        Contact contact1 = new Contact("John Locke");
        Contact contact2 = new Contact("Thomas Aquinas");

        // Act
        boolean b = contact1.equals(contact2);

        // Assert
        Assertions.assertFalse(b);
    }

    @Test
    @Order(7)
    @DisplayName("Comparing objects with different last names should return false")
    void contact_DifferentLastNamesShouldReturnFalse() {
        // Arrange
        Contact contact1 = new Contact("John Locke");
        Contact contact2 = new Contact("John Smith");

        // Act
        boolean b = contact1.equals(contact2);

        // Assert
        Assertions.assertFalse(b);
    }

    @Test()
    @Order(8)
    @DisplayName("Test for Contact's method hashCode")
    void parseContacts_hashCodeTest() {
        // Arrange
        Contact contact = new Contact("John Locke");
        int expected = 145343663;

        // Act
        int actual = contact.hashCode();

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    private static @NotNull Stream<Arguments> provideObjectsForHappyPaths() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                "ASC",
                new Contact[] {
                    new Contact("Thomas Aquinas"),
                    new Contact("Rene Descartes"),
                    new Contact("David Hume"),
                    new Contact("John Locke")
                }
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                "DESC",
                new Contact[] {
                    new Contact("Carl Gauss"),
                    new Contact("Leonhard Euler"),
                    new Contact("Paul Erdos")
                }
            ),
            Arguments.of(
                new String[] {},
                "DESC",
                new Contact[] {}
            ),
            Arguments.of(
                null,
                "DESC",
                new Contact[] {}
            ),
            Arguments.of(
                new String[] {"Paul", "Leonhard", "Carl"},
                "ASC",
                new Contact[] {
                    new Contact("Carl"),
                    new Contact("Leonhard"),
                    new Contact("Paul")
                }
            )
        );
    }
}
