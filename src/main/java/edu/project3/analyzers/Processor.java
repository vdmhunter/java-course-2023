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
import org.jetbrains.annotations.NotNull;
import static edu.project3.parser.NginxLogParser.parseLogFiles;
import static edu.project3.parser.NginxLogParser.parseLogLines;
import static edu.project3.provider.NginxLogsProvider.getLogFiles;
import static edu.project3.provider.NginxLogsProvider.readLogLinesFromUrl;
import static edu.project3.report.ReportGenerator.createSectionTitle;
import static edu.project3.report.ReportGenerator.createThreeColumnRow;
import static edu.project3.report.ReportGenerator.createTwoColumnRow;
import static edu.project3.report.ReportGenerator.generateReport;

public class Processor {
    private ProjectSettings settings;
    private List<Path> logFiles;
    private List<NginxLogEntry> logEntries;

    public Processor(String[] args) {
        setup(args);
        init();
    }

    public Report processAllAnalyzers() {
        List<String> tables = new ArrayList<>();

        tables.add(getMetricValueTable());
        tables.add(getMostPopularResourcesTable());
        tables.add(getMostPopularResponseCodesTable());
        tables.add(getMostPopularBrowsersTable());
        tables.add(getMostPopularOperationSystemsTable());

        ReportFormat format = settings.getFormat();

        return generateReport(tables, format);
    }

    private void setup(String[] args) {
        settings = new ProjectSettings();
        settings.parseArgs(args);
    }

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

    private boolean isInDateRange(@NotNull NginxLogEntry entry) {
        OffsetDateTime timeLocal = entry.timeLocal();
        OffsetDateTime from = settings.getFrom();
        OffsetDateTime to = settings.getTo();

        boolean afterFrom = (from == null || timeLocal.isAfter(from));
        boolean beforeTo = (to == null || timeLocal.isBefore(to));

        return afterFrom && beforeTo;
    }

    private @NotNull String getFileNames() {
        var list = new ArrayList<String>();

        for (var path : logFiles) {
            list.add(path.getFileName().toString());
        }

        return String.join(",", list);
    }

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

    // CHECKSTYLE:OFF: Disable MultipleStringLiterals check
    private @NotNull String getMostPopularResourcesTable() {
        return createSectionTitle("Resources requested")
            + createTwoColumnRow("Resource", "Count")
            + MostPopularResourcesAnalyzer.analyze(logEntries);
    }

    private @NotNull String getMostPopularResponseCodesTable() {
        return createSectionTitle("Response Codes")
            + createThreeColumnRow("Status", "Name", "Count")
            + MostPopularResponseCodesAnalyzer.analyze(logEntries);
    }

    private @NotNull String getMostPopularBrowsersTable() {
        return createSectionTitle("Browsers")
            + createTwoColumnRow("Browser", "Count")
            + MostPopularBrowsersAnalyzer.analyze(logEntries);
    }

    private @NotNull String getMostPopularOperationSystemsTable() {
        return createSectionTitle("Operation Systems")
            + createTwoColumnRow("Operation System", "Count")
            + MostPopularOperationSystemsAnalyzer.analyze(logEntries);
    }
    // CHECKSTYLE:ON: Disable MultipleStringLiterals check
}
