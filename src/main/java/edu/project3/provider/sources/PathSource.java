package edu.project3.provider.sources;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

public class PathSource implements Source {
    private final PathMatcher pathMatcher;

    public PathSource(String pattern) {
        pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/" + pattern);
    }

    public PathMatcher getPathMatcher() {
        return pathMatcher;
    }
}
