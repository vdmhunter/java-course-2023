package edu.hw6.task3;

import java.nio.file.Files;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code LargerThanFilter} interface extends the {@link AbstractFilter} interface
 * and provides a static method {@link LargerThanFilter#largerThan(long)} for creating a filter
 * that checks whether a file's size is larger than a specified size.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * Path dir = Paths.get("your_directory_path");
 *
 * try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, LargerThanFilter.largerThan(100_000))) {
 *     entries.forEach(System.out::println);
 * } catch (IOException e) {
 *     e.printStackTrace();
 * }
 * }
 * </pre>
 *
 * @see AbstractFilter
 * @see java.nio.file.Files
 * @see java.nio.file.Path
 */
public interface LargerThanFilter extends AbstractFilter {
    /**
     * Creates a filter that checks whether a file's size is larger than the specified size.
     *
     * @param size The size threshold for filtering files.
     * @return A filter that evaluates to {@code true} if the file's size is larger than the specified size,
     *     {@code false} otherwise.
     */
    @Contract(pure = true)
    static @NotNull AbstractFilter largerThan(long size) {
        return entry -> Files.size(entry) > size;
    }
}
