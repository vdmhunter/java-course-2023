package edu.hw6.task3;

import java.util.regex.Pattern;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code RegexContainsFilter} interface provides a static method {@link RegexContainsFilter#regexContains(String)}
 * for creating a filter that checks whether a file's name contains a specified regular expression pattern.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * Path dir = Paths.get("your_directory_path");
 *
 * try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, RegexContainsFilter.regexContains("[-]"))) {
 *     entries.forEach(System.out::println);
 * } catch (IOException e) {
 *     e.printStackTrace();
 * }
 * }
 * </pre>
 *
 * @see AbstractFilter
 * @see java.util.regex.Pattern
 * @see java.nio.file.Path
 */
public interface RegexContainsFilter {
    /**
     * Creates a filter that checks whether a file's name contains the specified regular expression pattern.
     *
     * @param regex The regular expression pattern to match against the file's name.
     * @return A filter that evaluates to {@code true} if the file's name contains the specified pattern,
     *     {@code false} otherwise.
     */
    @Contract(pure = true)
    static @NotNull AbstractFilter regexContains(String regex) {
        return entry -> {
            Pattern pattern = Pattern.compile(regex);

            return pattern.matcher(entry.toString()).find();
        };
    }
}
