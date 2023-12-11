package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code MagicNumberFilter} interface extends the {@link AbstractFilter} interface
 * and provides a static method {@link MagicNumberFilter#magicNumber(byte...)} for creating a filter
 * that checks whether a file's content matches a specified magic number pattern represented by an array of bytes.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * Path dir = Paths.get("your_directory_path");
 *
 * try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir,
 *     MagicNumberFilter.magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))) {
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
public interface MagicNumberFilter extends AbstractFilter {
    /**
     * Creates a filter that checks whether a file's content matches a specified magic number pattern.
     *
     * @param magicBytes The byte array representing the magic number pattern.
     * @return A filter that evaluates to {@code true} if the file's content matches the specified magic number pattern,
     *     {@code false} otherwise.
     */
    @Contract(pure = true)
    static @NotNull AbstractFilter magicNumber(byte... magicBytes) {
        return entry -> {
            try {
                byte[] fileBytes = Files.readAllBytes(entry);

                if (magicBytes.length > fileBytes.length) {
                    return false;
                }

                for (int i = 0; i < magicBytes.length; i++) {
                    if (fileBytes[i] != magicBytes[i]) {
                        return false;
                    }
                }

                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }
}
