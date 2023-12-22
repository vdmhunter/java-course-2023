package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import static edu.project3.report.ReportGenerator.createTwoColumnRow;

/**
 * An analyzer that calculates the total number of requests in a given list of Nginx log entries.
 * It generates a report with a single row displaying the total number of requests.
 */
public final class NumberOfRequestsAnalyzer extends Analyzer {
    private NumberOfRequestsAnalyzer() {
    }

    /**
     * Analyzes the given list of Nginx log entries and calculates the total number of requests.
     * Generates a report in the form of a two-column row, displaying the total number of requests.
     *
     * @param nginxLogEntries A list of Nginx log entries to be analyzed.
     * @return A formatted two-column row representing the total number of requests.
     */
    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogEntries) {
        return createTwoColumnRow("Total requests", Integer.toString(nginxLogEntries.size()));
    }
}
