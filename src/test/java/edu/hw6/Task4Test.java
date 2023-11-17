package edu.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static edu.hw6.task4.OutputStreamCompositionManager.write;

class Task4Test {
    @TempDir Path tempDir;

    @Test
    @DisplayName("Test write method in OutputStreamCompositionManager")
    void outputStreamCompositionManager_TestWrite() throws IOException {
        // Arrange
        String expected = "Programming is learned by writing programs. ― Brian Kernighan";
        Path file = tempDir.resolve("file.txt");
        Files.createFile(file);

        // Act
        write(file.toString(), expected);
        String actual = getFirstLineFromFile(file);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    private static String getFirstLineFromFile(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
