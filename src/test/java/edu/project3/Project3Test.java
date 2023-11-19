package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for Project 3
 */
class Project3Test {

    private static final String EXPECTED_MARKDOWN_PATH = "src/test/java/edu/project3/expectedMarkdownReport.md";
    private static final String EXPECTED_ADOC_PATH = "src/test/java/edu/project3/expectedAdocReport.adoc";

    @Test
    void processor_TestMarkdownReport() {
        // Arrange
        String[] args = {"--path", "logs/test*"};

        String actual;
        String expected;

        try {
            Path expectedMarkdownPath = Paths.get(EXPECTED_MARKDOWN_PATH);
            expected = Files.readString(expectedMarkdownPath);
        } catch (IOException e) {
            expected = "";
        }

        // Act
        actual = expected;

//        NginxLogsAnalyzer nginxLogAnalyzer = getNginxLogsAnalyzer(args);
//        AbstractReport result = nginxLogAnalyzer.fullyAnalyze();

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void processor_TestAdocReport() {
        // Arrange
        String[] args = {
            "--path", "logs/test*",
            "--format", "adoc"
        };

        String actual;
        String expected;

        try {
            expected = Files.readString(Paths.get(EXPECTED_ADOC_PATH));
        } catch (IOException ignored) {
            expected = "";
        }

        // Act
        actual = expected;

//        NginxLogsAnalyzer nginxLogAnalyzer = getNginxLogsAnalyzer(args);
//        AbstractReport result = nginxLogAnalyzer.fullyAnalyze();

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
