package edu.hw6;

import edu.hw6.task2.FileCloner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests for Homework 6, Task 2
 */
class Task2Test {
    @TempDir Path tempDir;

    @Test
    @DisplayName("Test cloneFile operation in FileCloner")
    void fileCloner_TestCloneFile() {
        try {
            // Arrange
            Path originalFile = tempDir.resolve("original.txt");
            Files.createFile(originalFile);

            // Act
            FileCloner.cloneFile(originalFile);
            FileCloner.cloneFile(originalFile);
            Path copiedFile1 = tempDir.resolve("original — copy.txt");
            Path copiedFile2 = tempDir.resolve("original — copy (2).txt");

            // Assert
            Assertions.assertAll(
                () -> Assertions.assertTrue(Files.exists(copiedFile1), "Copied file should exist"),
                () -> Assertions.assertTrue(Files.exists(copiedFile2), "Copied file should exist")
            );
        } catch (IOException e) {
            Assertions.fail("Test failed due to IOException: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test cloneFile operation with IOException in FileCloner")
    void fileCloner_TestCloneFileIOException() {
        // Arrange
        Path nonExistentFile = tempDir.resolve("nonExistent.txt");

        // Act & Assert
        Assertions.assertThrows(IOException.class, () -> FileCloner.cloneFile(nonExistentFile));
    }
}
