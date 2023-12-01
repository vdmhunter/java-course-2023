package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

/**
 * The {@code AbstractFilter} interface extends the {@code DirectoryStream.Filter<Path>} interface,
 * providing additional functionality for combining filters using logical AND operation.
 *
 * <p>This interface allows users to create composite filters by chaining multiple filters together,
 * creating a logical AND relationship between them.
 *
 * <p>Implementing classes should override the {@code accept} method to define the filtering logic for a given entry.
 * The {@code and} method enables the combination of two filters, creating a new filter that represents the logical AND
 * of the original filters.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * Path dir = Paths.get("your_directory_path");
 *
 * try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, AbstractFilter.regularFile
 *         .and(AbstractFilter.readable)
 *         .and(AbstractFilter.sizeGreaterThan(100_000))
 *         .and(AbstractFilter.magicNumber(0x89, 'P', 'N', 'G'))
 *         .and(AbstractFilter.globMatches("*.png"))
 *         .and(AbstractFilter.regexContains("[-]")))) {
 *
 *     entries.forEach(System.out::println);
 * } catch (IOException e) {
 *     e.printStackTrace();
 * }
 * }
 * </pre>
 *
 * @see java.nio.file.DirectoryStream.Filter
 * @see java.nio.file.Path
 */
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    /**
     * Combines this filter with another filter using a logical AND operation.
     *
     * @param other The other filter to be combined with this filter.
     * @return A new filter representing the logical AND of this filter and the provided filter.
     */
    default AbstractFilter and(AbstractFilter other) {
        return entry -> this.accept(entry) && other.accept(entry);
    }
}
