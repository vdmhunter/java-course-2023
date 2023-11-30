package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code FileCloner} class provides a method to clone a file with a new name.
 */
public final class FileCloner {
    private static final Logger LOGGER = LogManager.getLogger();

    private FileCloner() {
    }

    /**
     * Clones the specified file with a new name.
     *
     * @param filePath The path to the file to be cloned.
     * @throws IOException If an I/O error occurs during the file cloning process.
     */
    public static void cloneFile(@NotNull Path filePath) throws IOException {
        try {
            String fileName = filePath.getFileName().toString();
            String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
            String extension = fileName.substring(fileName.lastIndexOf('.'));

            int copyNumber = 0;
            String copyFileName = fileName;

            while (Files.exists(filePath.resolveSibling(copyFileName))) {
                copyNumber++;
                copyFileName = baseName + " — copy" + (copyNumber > 1 ? " (" + copyNumber + ")" : "") + extension;
            }

            Files.copy(filePath, filePath.resolveSibling(copyFileName), StandardCopyOption.REPLACE_EXISTING);

            LOGGER.info("The file has been successfully copied: {}\n", copyFileName);
        } catch (IOException e) {
            LOGGER.error("Error when copying a file: {}\n", e.getMessage());

            throw e;
        }
    }
}
