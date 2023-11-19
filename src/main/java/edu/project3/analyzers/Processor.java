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
import static edu.project3.report.ReportGenerator.createSingleColumnRow;
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

        tables.add(getFileNamesTable());
//        tables.add(getMetricValue5x2Table());
//        tables.add(getResourceCount6x2Table());
//        tables.add(getStatusNameCount6x3Table());
//        tables.add(getCategoryCountPercent6x3Table());
//        tables.add(getTimeOfDayCountPercent6x3Table());

        ReportFormat format = settings.getFormat();

        return format != null
            ? generateReport(tables, format)
            : generateReport(tables, ReportFormat.MARKDOWN);
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

        return (from == null || timeLocal.isAfter(from)) && (to == null || timeLocal.isBefore(to));
    }

    private String getFileNamesTable() {
        return createSingleColumnRow("File name") + getFileNames();
    }

    private String getFileNames() {
        var builder = new StringBuilder();

        for (var path : logFiles) {
            builder.append(createSingleColumnRow(path.getFileName().toString()));
        }

        return builder.toString();
    }
}
