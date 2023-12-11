package edu.hw6.task3;

import java.nio.file.Files;
import java.nio.file.Path;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code IsReadableFilter} interface provides a static method {@link IsReadableFilter#readable()}
 * for creating a filter that checks whether a file is readable using the {@link java.nio.file.Files#isReadable(Path)}
 * method.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * Path dir = Paths.get("your_directory_path");
 *
 * try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, IsReadableFilter.readable())) {
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
public interface IsReadableFilter extends AbstractFilter {
    /**
     * Creates a filter that checks whether a file is readable.
     *
     * @return A filter that evaluates to {@code true} if the file is readable, {@code false} otherwise.
     */
    @Contract(pure = true)
    static @NotNull AbstractFilter readable() {
        return Files::isReadable;
    }
}
