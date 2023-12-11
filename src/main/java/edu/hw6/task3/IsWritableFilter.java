package edu.hw6.task3;

import java.nio.file.Files;
import java.nio.file.Path;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code IsWritableFilter} interface extends the {@link AbstractFilter} interface
 * and provides a static method {@link IsWritableFilter#writable()} for creating a filter that checks
 * whether a file is writable using the {@link java.nio.file.Files#isWritable(Path)} method.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * Path dir = Paths.get("your_directory_path");
 *
 * try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, IsWritableFilter.writable())) {
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
public interface IsWritableFilter extends AbstractFilter {
    /**
     * Creates a filter that checks whether a file is writable.
     *
     * @return A filter that evaluates to {@code true} if the file is writable, {@code false} otherwise.
     */
    @Contract(pure = true)
    static @NotNull AbstractFilter writable() {
        return Files::isWritable;
    }
}
