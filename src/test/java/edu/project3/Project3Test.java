package edu.project3;

import edu.project3.analyzers.Processor;
import edu.project3.provider.NginxLogsProvider;
import edu.project3.provider.sources.UrlSource;
import edu.project3.report.Report;
import edu.project3.settings.ProjectSettings;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Project 3
 */
class Project3Test {

    private static final String EXPECTED_MD_REPORT_PATH_1 = "src/test/java/edu/project3/expectedMarkdownReport1.md";
    private static final String EXPECTED_MD_REPORT_PATH_2 = "src/test/java/edu/project3/expectedMarkdownReport2.md";
    private static final String EXPECTED_ADOC_REPORT_PATH = "src/test/java/edu/project3/expectedAdocReport1.adoc";

    @Test
    @DisplayName("Test markdown report generation from local path")
    void processor_TestMarkdownReportGenerationByLocalPath() {
        // Arrange
        String[] args = {"--path", "*nginx*.txt", "--format", "markdown"};

        String actual;
        String expected;

        try {
            Path expectedMarkdownPath = Paths.get(EXPECTED_MD_REPORT_PATH_1);
            expected = Files.readString(expectedMarkdownPath);
        } catch (IOException e) {
            expected = "";
        }

        // Act
        Processor nginxLogProcessor = new Processor(args);
        Report report = nginxLogProcessor.processAllAnalyzers();
        actual = report.content();

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test AsciiDoc report generation from local path")
    void processor_TestAdocReportGenerationByLocalPath() {
        // Arrange
        String[] args = {"--path", "*nginx*.txt", "--format", "adoc"};

        String actual;
        String expected;

        try {
            expected = Files.readString(Paths.get(EXPECTED_ADOC_REPORT_PATH));
        } catch (IOException ignored) {
            expected = "";
        }

        // Act
        Processor nginxLogProcessor = new Processor(args);
        Report report = nginxLogProcessor.processAllAnalyzers();
        actual = report.content();

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test markdown report when source provided by URL")
    void processor_TestMarkdownReportGenerationByUrl() {
        // Arrange
        String[] args = {
            "--path",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "--from",
            "2015-05-20T00:00:00+00:00",
            "--to",
            "2015-06-01T00:00:00+00:00"
        };

        String actual;
        String expected;

        try {
            Path expectedMarkdownPath = Paths.get(EXPECTED_MD_REPORT_PATH_2);
            expected = Files.readString(expectedMarkdownPath);
        } catch (IOException e) {
            expected = "";
        }

        // Act
        Processor nginxLogProcessor = new Processor(args);
        Report report = nginxLogProcessor.processAllAnalyzers();
        actual = report.content();

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test UrlSource construction with invalid URL pattern")
    void projectSettings_TestParseArgs() {
        // Arrange
        ProjectSettings projectSettings = new ProjectSettings();
        String[] args = {};

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> projectSettings.parseArgs(args));
    }

    @Test
    @DisplayName("Test UrlSource construction with invalid URL pattern")
    void urlSource_TestUrlSourceConstructionWithInvalidUrlPattern() {
        // Arrange
        String invalidUrlPattern = "httpa://invalid-url";

        // Act & Assert
        Assertions.assertThrows(MalformedURLException.class, () -> new UrlSource(invalidUrlPattern));
    }

    @Test
    @DisplayName("Test readLogLinesFromUrl with invalid URL")
    void NginxLogsProvider_TestReadLogLinesFromUrlWithInvalidUrl() throws MalformedURLException {
        // Arrange
        @SuppressWarnings("deprecation")
        var invalidUrl = new URL("https://nonexistent-url.com");

        // Act
        List<String> logLines = NginxLogsProvider.readLogLinesFromUrl(invalidUrl);

        // Assert
        Assertions.assertTrue(logLines.isEmpty());
    }
}
