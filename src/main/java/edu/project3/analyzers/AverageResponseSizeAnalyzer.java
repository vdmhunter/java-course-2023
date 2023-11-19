package edu.project3.analyzers;

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
        double averageResponse = nginxLogItems.stream()
            .mapToLong(NginxLogEntry::bodyBytesSent)
            .average()
            .orElse(0.0);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("en-US"));
        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);

        return createTwoColumnRow(AVERAGE_SERVER_RESPONSE, decimalFormat.format(averageResponse));
    }
}
