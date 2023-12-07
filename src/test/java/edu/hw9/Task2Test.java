package edu.hw9;

import edu.hw9.task2.DirSearchTask;
import edu.hw9.task2.FileSearchTask;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests for Homework 9, Task 2
 */
class Task2Test {
    @TempDir Path tempDir;

    @BeforeEach
    void setup() throws IOException {
        createSubFolderWithFilesInTempDir("testFolder1_",101);
        createSubFolderWithFilesInTempDir("testFolder2_",1_001);
        createFileInTempDir("file1.txt", 1_024);
        createFileInTempDir("file2.tmp", 512);
    }

    @Test
    @DisplayName("Search for folders with more than 1000 files")
    void dirSearchTask_TestSearchFoldersWithMoreThan1000Files() {
        // Arrange
        List<String> directories;
        File startSearchPath = tempDir.toFile();


        // Act
        try (ForkJoinPool pool = new ForkJoinPool()) {
            var task = new DirSearchTask(startSearchPath, 1_000);
            directories = pool.invoke(task);
        }

        // Assert
        Assertions.assertTrue(directories.stream().anyMatch(s -> s.contains("testFolder2_")));
    }

    @Test
    @DisplayName("Search for files larger than 1KB with '.txt' extension")
    void fileSearchTask_TestSearchSpecifiedFiles() {
        // Arrange
        List<String> files;
        File startSearchPath = tempDir.toFile();

        // Act
        try (ForkJoinPool pool = new ForkJoinPool()) {
            var task = new FileSearchTask(startSearchPath, 1_000, "txt");
            files = pool.invoke(task);
        }

        // Assert
        Assertions.assertTrue(files.stream().anyMatch(s -> s.contains("file1.txt")));
    }


    private void createSubFolderWithFilesInTempDir(String folderPrefix, int numberOfFiles) throws IOException {
        Path folder = Files.createTempDirectory(tempDir, folderPrefix);

        for (int i = 0; i < numberOfFiles; i++) {
            Files.createTempFile(folder, "testFile", ".tmp");
        }
    }

    private void createFileInTempDir(String fileName, int numberOfChars) throws IOException {
        Path filePath = tempDir.resolve(fileName);
        String chars = "*".repeat(numberOfChars);

        Files.write(filePath, chars.getBytes());
    }
}
