package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * The {@code DirSearchTask} that recursively searches for directories with a specified number of files or more.
 * The search is performed in a parallel manner using the Fork-Join framework.
 */
public class DirSearchTask extends RecursiveTask<List<String>> {
    /**
     * The directory to start the search from.
     */
    private final File directory;
    /**
     * The minimum number of files a directory must contain to be considered in the result.
     */
    private final int numberOfFiles;

    /**
     * Constructs a new {@code DirSearchTask} with the specified starting directory and minimum number of files.
     *
     * @param directory     The directory to start the search from.
     * @param numberOfFiles The minimum number of files a directory must contain to be considered in the result.
     */
    public DirSearchTask(File directory, int numberOfFiles) {
        this.directory = directory;
        this.numberOfFiles = numberOfFiles;
    }

    /**
     * Performs the computation to search for directories with a specified number of files or more.
     *
     * @return A list of directory paths that meet the criteria.
     */
    @Override
    protected List<String> compute() {
        int count = 0;
        List<String> directories = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    var counter = new DirSearchTask(file, numberOfFiles);
                    counter.fork();
                    directories.addAll(counter.join());
                } else {
                    count++;
                }
            }
        }

        if (count > numberOfFiles) {
            directories.add(directory.getPath());
        }

        return directories;
    }
}
