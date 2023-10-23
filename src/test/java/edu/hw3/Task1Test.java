package edu.hw3;

import edu.hw3.task1.Encoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Homework 3, Task 1
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task1Test {
    @ParameterizedTest(name = "Test {index} - Encode \"{0}\" with Atbash should return \"{1}\"")
    @Order(1)
    @CsvSource({
        "Hello world!,Svool dliow!",
        "Any fool can write code that a computer can understand. " +
            "Good programmers write code that humans can understand. ― Martin Fowler," +
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
        "Привет мир!,Привет мир!"
    })
    @DisplayName("Happy paths")
    void atbash_HappyPaths(String str, String expected) {
        // Act
        String actual = Encoder.atbash(str);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    @DisplayName("Test when input string is null")
    void atbash_InputStringIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> Encoder.atbash(null));
    }

    @Test
    @Order(3)
    @DisplayName("Test when input string is empty")
    void atbash_InputStringIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Encoder.atbash(""));
    }
}
