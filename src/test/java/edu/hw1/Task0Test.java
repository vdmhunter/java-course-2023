package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 1, Task 0
 */
public class Task0Test {
    @Test
    @DisplayName("Task0.helloWord() returns 'Привет, мир!'")
    void helloWord_shouldReturnExpectedString() {
        String expected = "Привет, мир!";
        String actual = Task0.helloWord();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Task0.helloWord() does not return 'Hello world!'")
    void helloWord_shouldNotReturnUnexpectedString() {
        String unexpected = "Hello world!";
        String actual = Task0.helloWord();

        Assertions.assertNotEquals(unexpected, actual);
    }
}
