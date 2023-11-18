package edu.hw6;

import edu.hw6.task3.AbstractFilter;
import edu.hw6.task3.MagicNumberFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static edu.hw6.task3.GlobMatchesFilter.globMatches;
import static edu.hw6.task3.IsReadableFilter.readable;
import static edu.hw6.task3.IsWritableFilter.writable;
import static edu.hw6.task3.LargerThanFilter.largerThan;
import static edu.hw6.task3.MagicNumberFilter.magicNumber;
import static edu.hw6.task3.RegexContainsFilter.regexContains;

/**
 * Tests for Homework 6, Task 3
 */
class Task3Test {
    @TempDir Path tempDir;

    @Test
    @DisplayName("Filtering files using AbstractFilters")
    void filter_Test() throws IOException {
        // Arrange
        Path sourceFile = Paths.get("src", "main", "resources", "hw6", "java.png");
        Path destinationFile = tempDir.resolve("java.png");
        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);

        Path testFile01 = tempDir.resolve("test01.txt");
        Files.createFile(testFile01);
        Path testFile02 = tempDir.resolve("test02.txt");
        Files.createFile(testFile02);

        List<String> expected = new ArrayList<>();
        expected.add("java.png");

        AbstractFilter regularFile = Files::isRegularFile;
        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable())
            .and(writable())
            .and(largerThan(4_000))
            .and(magicNumber(new byte[] {(byte) 0x89, 'P', 'N', 'G'}))
            .and(globMatches("*.png"))
            .and(regexContains("[j]"));

        // Act
        var actual = new ArrayList<String>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(tempDir, filter)) {
            entries.forEach(filePath ->
                actual.add(filePath.getFileName().toString())
            );
        }

        //Assert
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    @DisplayName("MagicNumberFilter return false for a file without matching magic bytes")
    void magicNumberFilter_NoMatch() throws IOException {
        // Arrange
        byte[] magicBytes = {(byte) 0x89, 'P', 'N', 'G'};
        Path file = tempDir.resolve("file.txt");
        Files.createFile(file);
        Files.writeString(file, "content");
        AbstractFilter filter = MagicNumberFilter.magicNumber(magicBytes);

        // Act
        boolean actual = filter.accept(file);

        // Assert
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("MagicNumberFilter return false for a file with content shorter than magic bytes")
    void magicNumberFilter_ContentShorterThanMagicBytes() throws IOException {
        // Arrange
        byte[] magicBytes = {(byte) 0x89, 'P', 'N', 'G'};
        Path file = tempDir.resolve("file.txt");
        Files.createFile(file);
        Files.writeString(file, "abc");
        AbstractFilter filter = MagicNumberFilter.magicNumber(magicBytes);

        // Act
        boolean actual = filter.accept(file);

        // Assert
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("MagicNumberFilter throw IOException")
    void magicNumberFilter_IOException_ReturnsFalse() throws IOException {
        // Arrange
        byte[] magicBytes = {(byte) 0x89, 'P', 'N', 'G'};
        Path nonExistentFile = tempDir.resolve("nonExistent.txt");
        AbstractFilter filter = MagicNumberFilter.magicNumber(magicBytes);

        // Act
        boolean actual = filter.accept(nonExistentFile);

        // Assert
        Assertions.assertFalse(actual);
    }
}
