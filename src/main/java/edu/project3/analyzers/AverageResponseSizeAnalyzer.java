package edu.project3.analyzers;

import edu.common.Generated;
import edu.project3.parser.NginxLogEntry;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import static edu.project3.report.ReportGenerator.createTwoColumnRow;

/**
 * An analyzer specifically designed to calculate and report the average response size
 * based on a list of Nginx log entries.
 */
public final class AverageResponseSizeAnalyzer extends Analyzer {
    private AverageResponseSizeAnalyzer() {
    }

    /**
     * Analyzes the given list of Nginx log entries and calculates the average response size.
     * The result is formatted as a two-column row suitable for reporting.
     *
     * @param nginxLogEntries A list of Nginx log entries to be analyzed.
     * @return A formatted two-column row representing the average server response size.
     */
    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogEntries) {
        long totalBytesSent = 0L;
        int count = 0;

        for (NginxLogEntry entry : nginxLogEntries) {
            totalBytesSent += entry.bodyBytesSent();
            count++;
        }

        double averageResponse = getAverageResponse(count, totalBytesSent);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("en-US"));
        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);

        return createTwoColumnRow("Average server response", decimalFormat.format(averageResponse));
    }

    /**
     * Calculates the average response size given the count of entries and the total bytes sent.
     *
     * @param count          The count of entries.
     * @param totalBytesSent The total number of bytes sent in the entries.
     * @return The calculated average response size, or 0.0 if the count is zero.
     */
    @Generated
    private static double getAverageResponse(int count, double totalBytesSent) {
        return (count > 0)
            ? (totalBytesSent / count)
            : 0.0;
    }
}
