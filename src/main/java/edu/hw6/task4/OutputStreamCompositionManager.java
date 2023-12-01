package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

/**
 * The {@code OutputStreamCompositionManager} class provides a utility for writing a message to a file
 * while calculating a CRC32 checksum for data integrity.
 * <p>
 * This class is designed for composing various output stream components in a try-with-resources block
 * to ensure proper resource management.
 * </p>
 */
public final class OutputStreamCompositionManager {
    private OutputStreamCompositionManager() {
    }

    /**
     * Writes the specified message to the given file, calculating a CRC32 checksum for data integrity.
     * The method uses a combination of output stream components for efficient and secure writing.
     *
     * @param fileName The name of the file to write the message to.
     * @param message  The message to be written to the file.
     * @throws IOException If an I/O error occurs during the writing process.
     */
    public static void write(String fileName, String message) throws IOException {
        try (var outputStream = new FileOutputStream(fileName);
             var checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
             var bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             var outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
             var printWriter = new PrintWriter(outputStreamWriter)
        ) {
            printWriter.println(message);
        }
    }
}
