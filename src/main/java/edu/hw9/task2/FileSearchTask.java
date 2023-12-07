package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code FileSearchTask} that recursively searches for files based on size and extension.
 * The search is performed in a parallel manner using the Fork-Join framework.
 */
public class FileSearchTask extends RecursiveTask<List<String>> {
    /**
     * The file to start the search from.
     */
    private final File file;
    /**
     * The minimum size a file must have to be considered in the result.
     */
    private final long size;
    /**
     * The file extension to filter files.
     */
    private final String extension;

    /**
     * Constructs a new {@code FileSearchTask} with the specified starting file, minimum size, and file extension.
     *
     * @param file      The file to start the search from.
     * @param size      The minimum size a file must have to be considered in the result.
     * @param extension The file extension to filter files.
     */
    public FileSearchTask(File file, long size, String extension) {
        this.file = file;
        this.size = size;
        this.extension = extension;
    }

    /**
     * Performs the computation to search for files based on size and extension.
     *
     * @return A list of file paths that meet the criteria.
     */
    @Override
    protected List<String> compute() {
        List<String> foundFiles = new ArrayList<>();
        searchFiles(file, foundFiles);

        return foundFiles;
    }

    /**
     * Recursively searches for files in the specified directory and processes each file.
     *
     * @param currentFile The current file or directory being processed.
     * @param foundFiles  The list to store paths of files that meet the criteria.
     */
    private void searchFiles(@NotNull File currentFile, List<String> foundFiles) {
        File[] files = currentFile.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    searchFiles(f, foundFiles);
                } else {
                    processFile(f, foundFiles);
                }
            }
        }
    }

    /**
     * Processes an individual file, adding its path to the list if it meets the specified criteria.
     *
     * @param currentFile The file to process.
     * @param foundFiles  The list to store paths of files that meet the criteria.
     */
    private void processFile(@NotNull File currentFile, List<String> foundFiles) {
        if (currentFile.length() > size && currentFile.getName().endsWith(extension)) {
            foundFiles.add(currentFile.getPath());
        }
    }
}
