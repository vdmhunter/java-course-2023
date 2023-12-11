package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import edu.project3.provider.sources.PathSource;
import edu.project3.provider.sources.Source;
import edu.project3.provider.sources.UrlSource;
import edu.project3.report.Report;
import edu.project3.report.ReportFormat;
import edu.project3.settings.ProjectSettings;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import static edu.project3.parser.NginxLogParser.parseLogFiles;
import static edu.project3.parser.NginxLogParser.parseLogLines;
import static edu.project3.provider.NginxLogsProvider.getLogFiles;
import static edu.project3.provider.NginxLogsProvider.readLogLinesFromUrl;
import static edu.project3.report.ReportGenerator.createSectionTitle;
import static edu.project3.report.ReportGenerator.createThreeColumnRow;
import static edu.project3.report.ReportGenerator.createTwoColumnRow;
import static edu.project3.report.ReportGenerator.generateReport;

/**
 * The main processor for analyzing Nginx log entries and generating reports based on various metrics.
 */
public class Processor {
    private ProjectSettings settings;
    private List<Path> logFiles;
    private List<NginxLogEntry> logEntries;
    private static final String COUNT = "Count";

    public Processor(String[] args) {
        setup(args);
        init();
    }

    /**
     * Processes all analyzers and generates a report based on the configured settings.
     *
     * @return The generated report containing various metrics and analysis results.
     */
    public Report processAllAnalyzers() {
        List<String> tables = new ArrayList<>();

        tables.add(getMetricValueTable());
        tables.add(getMostPopularResourcesTable());
        tables.add(getMostPopularResponseCodesTable());
        tables.add(getMostPopularBrowsersTable());
        tables.add(getMostPopularOperatingSystemsTable());

        ReportFormat format = settings.getFormat();

        return generateReport(tables, format);
    }

    /**
     * Initializes the project settings based on the provided command-line arguments.
     *
     * @param args Command-line arguments used to configure the project settings.
     */
    private void setup(String[] args) {
        settings = new ProjectSettings();
        settings.parseArgs(args);
    }

    /**
     * Initializes the {@code Processor} by determining the source type (URL or file path),
     * reading log entries accordingly, and filtering log entries based on the specified date range.
     */
    private void init() {
        logFiles = new ArrayList<>();
        Source source = settings.getSource();

        if (source instanceof UrlSource urlSource) {
            logFiles.add(Path.of(urlSource.getURL().getFile()));
            logEntries = parseLogLines(
                readLogLinesFromUrl(urlSource.getURL())
            );
        } else if (source instanceof PathSource pathMatcherSource) {
            logFiles.addAll(getLogFiles(pathMatcherSource.getPathMatcher()));
            logEntries = parseLogFiles(logFiles);
        }

        logEntries = logEntries.stream()
            .filter(this::isInDateRange)
            .toList();
    }

    /**
     * Checks whether a given Nginx log entry falls within the specified date range.
     *
     * @param entry The Nginx log entry to check.
     * @return {@code true} if the entry is within the date range, {@code false} otherwise.
     */
    private boolean isInDateRange(@NotNull NginxLogEntry entry) {
        OffsetDateTime timeLocal = entry.timeLocal();
        OffsetDateTime from = settings.getFrom();
        OffsetDateTime to = settings.getTo();

        boolean afterFrom = (Objects.isNull(from) || timeLocal.isAfter(from));
        boolean beforeTo = (Objects.isNull(to) || timeLocal.isBefore(to));

        return afterFrom && beforeTo;
    }

    /**
     * Retrieves the file names from the list of log files.
     *
     * @return A comma-separated string of file names.
     */
    private @NotNull String getFileNames() {
        var list = new ArrayList<String>();

        for (var path : logFiles) {
            list.add(path.getFileName().toString());
        }

        return String.join(",", list);
    }

    /**
     * Generates a table with general metric information, such as file names, date range, total requests,
     * and average response size.
     *
     * @return A formatted string representing the metric value table.
     */
    private @NotNull String getMetricValueTable() {
        var sb = new StringBuilder();

        String from = settings.getFrom() != null ? settings.getFrom().toString() : "-";
        String to = settings.getTo() != null ? settings.getTo().toString() : "-";

        sb.append(createSectionTitle("General information"))
            .append(createTwoColumnRow("Metric", "Value"))
            .append(createTwoColumnRow("File name", getFileNames()))
            .append(createTwoColumnRow("From date", from))
            .append(createTwoColumnRow("To date", to))
            .append(NumberOfRequestsAnalyzer.analyze(logEntries))
            .append(AverageResponseSizeAnalyzer.analyze(logEntries));

        return sb.toString();
    }

    /**
     * Generates a table with information about the most popular resources requested.
     *
     * @return A formatted string representing the most popular resources table.
     */
    private @NotNull String getMostPopularResourcesTable() {
        return createSectionTitle("Resources requested")
            + createTwoColumnRow("Resource", COUNT)
            + MostPopularResourcesAnalyzer.analyze(logEntries);
    }

    /**
     * Generates a table with information about the most popular HTTP response codes.
     *
     * @return A formatted string representing the most popular response codes table.
     */
    private @NotNull String getMostPopularResponseCodesTable() {
        return createSectionTitle("Response Codes")
            + createThreeColumnRow("Status", "Name", COUNT)
            + MostPopularResponseCodesAnalyzer.analyze(logEntries);
    }

    /**
     * Generates a table with information about the most popular web browsers.
     *
     * @return A formatted string representing the most popular browsers table.
     */
    private @NotNull String getMostPopularBrowsersTable() {
        return createSectionTitle("Browsers")
            + createTwoColumnRow("Browser", COUNT)
            + MostPopularBrowsersAnalyzer.analyze(logEntries);
    }

    /**
     * Generates a table with information about the most popular operating systems.
     *
     * @return A formatted string representing the most popular operating systems table.
     */
    private @NotNull String getMostPopularOperatingSystemsTable() {
        return createSectionTitle("Operating Systems")
            + createTwoColumnRow("Operating System", COUNT)
            + MostPopularOperatingSystemsAnalyzer.analyze(logEntries);
    }
}
