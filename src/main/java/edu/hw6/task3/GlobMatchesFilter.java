package edu.hw6.task3;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code GlobMatchesFilter} interface extends the {@link AbstractFilter} interface,
 * providing additional functionality for filtering entries based on glob patterns.
 *
 * <p>This interface contains a static method {@link GlobMatchesFilter#globMatches(String)} that creates a filter
 * based on the provided glob pattern.
 * The resulting filter checks if the entry's file name matches the specified glob pattern.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * Path dir = Paths.get("your_directory_path");
 *
 * try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, GlobMatchesFilter.globMatches("*.txt"))) {
 *     entries.forEach(System.out::println);
 * } catch (IOException e) {
 *     e.printStackTrace();
 * }
 * }
 * </pre>
 *
 * @see AbstractFilter
 * @see java.nio.file.FileSystems
 * @see java.nio.file.PathMatcher
 */
public interface GlobMatchesFilter extends AbstractFilter {
    /**
     * Creates a filter based on the specified glob pattern.
     *
     * @param glob The glob pattern to match against entry file names.
     * @return A filter that checks if the entry's file name matches the provided glob pattern.
     */
    @Contract(pure = true)
    static @NotNull AbstractFilter globMatches(String glob) {
        return entry -> {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);

            return matcher.matches(entry.getFileName());
        };
    }
}
