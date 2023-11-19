package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import static edu.project3.report.ReportGenerator.createTwoColumnRow;

public final class NumberOfRequestsAnalyzer {

    private NumberOfRequestsAnalyzer() {
    }

    private static final String NUMBER_OF_REQUESTS = "Total requests";

    public static @NotNull String analyze(@NotNull List<NginxLogEntry> entries) {
        return createTwoColumnRow(NUMBER_OF_REQUESTS, Integer.toString(entries.size()));
    }
}
