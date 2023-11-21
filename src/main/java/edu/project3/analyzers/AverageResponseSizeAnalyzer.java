package edu.project3.analyzers;

import edu.common.Generated;
import edu.project3.parser.NginxLogEntry;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import static edu.project3.report.ReportGenerator.createTwoColumnRow;

public final class AverageResponseSizeAnalyzer {
    private AverageResponseSizeAnalyzer() {
    }

    private static final String AVERAGE_SERVER_RESPONSE = "Average server response";

    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogItems) {
        long totalBytesSent = 0L;
        int count = 0;

        for (NginxLogEntry entry : nginxLogItems) {
            totalBytesSent += entry.bodyBytesSent();
            count++;
        }

        double averageResponse = getAverageResponse(count, totalBytesSent);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("en-US"));
        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);

        return createTwoColumnRow(AVERAGE_SERVER_RESPONSE, decimalFormat.format(averageResponse));
    }

    @Generated
    private static double getAverageResponse(int count, double totalBytesSent) {
        return (count > 0) ? (totalBytesSent / count) : 0.0;
    }
}
