package edu.project3.provider.sources;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

/**
 * A {@code PathSource} represents a source of Nginx log entries based on file paths matched by a specified pattern.
 */
public class PathSource implements Source {
    private final PathMatcher pathMatcher;

    /**
     * Constructs a {@code PathSource} with the specified pattern for matching file paths.
     *
     * @param pattern The pattern used for matching file paths, following the "glob" syntax.
     *                The pattern is applied to all files recursively within the file system.
     */
    public PathSource(String pattern) {
        pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/" + pattern);
    }

    /**
     * Gets the {@code PathMatcher} associated with this {@code PathSource}.
     *
     * @return The {@code PathMatcher} used for matching file paths against a specified pattern.
     */
    public PathMatcher getPathMatcher() {
        return pathMatcher;
    }
}
