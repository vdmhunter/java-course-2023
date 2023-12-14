package edu.hw9;

import edu.hw9.task2.DirSearchTask;
import edu.hw9.task2.FileSearchTask;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
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
    boolean thousandFilesFlag = false;
    boolean largeFileSizeFlag = false;
    @TempDir Path tempDir;

    @BeforeEach
    void setup() throws IOException {
        thousandFilesFlag = false;
        largeFileSizeFlag = false;

        createRandomFolderStructure(tempDir, 5);
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
        Assertions.assertTrue(directories.stream().anyMatch(s -> s.contains("SubFolderWithMoreThan1000Files_")));
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
        Assertions.assertTrue(files.stream().anyMatch(s -> s.contains("FileWithSizeMoreThan1000_")));
    }

    private void createRandomFolderStructure(Path currentDir, int remainingSubFolders) throws IOException {
        if (remainingSubFolders <= 0) {
            return;
        }

        Random random = new Random();

        int subFolderCount = random.nextInt(5) + 1;

        for (int i = 0; i < subFolderCount; i++) {
            String subFolderPrefix = "SubFolder_";
            int fileCount = random.nextInt(51) + 50;

            if (!thousandFilesFlag && random.nextInt(11) == 5) {
                fileCount = 1_001;
                subFolderPrefix = "SubFolderWithMoreThan1000Files_";
                thousandFilesFlag = true;
            }

            Path subFolder = Files.createDirectory(currentDir.resolve(subFolderPrefix + i));

            createFiles(subFolder, fileCount, random);

            createRandomFolderStructure(subFolder, remainingSubFolders - 1);
        }
    }

    private void createFiles(Path subFolder, int fileCount, Random random) throws IOException {
        for (int j = 0; j < fileCount; j++) {
            String filePrefix = "File_";
            String fileExt = ".tmp";

            if (!largeFileSizeFlag && random.nextInt(101) == 50) {
                fileCount = 1_000;
                filePrefix = "FileWithSizeMoreThan1000_";
                fileExt = ".txt";
                largeFileSizeFlag = true;
            }

            var path = Files.createFile(subFolder.resolve(filePrefix + j + fileExt));

            if (path.getFileName().toString().startsWith("FileWithSizeMoreThan1000_")) {
                Files.write(path, "*".repeat(1_024).getBytes());
            }
        }
    }
}
