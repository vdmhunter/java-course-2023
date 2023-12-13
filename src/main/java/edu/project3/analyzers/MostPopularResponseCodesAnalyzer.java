package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import static edu.project3.report.ReportGenerator.createThreeColumnRow;

/**
 * An analyzer that identifies the most popular HTTP response codes based on Nginx log entries.
 * It counts the occurrences of each response code and generates a report with additional details.
 */
public final class MostPopularResponseCodesAnalyzer extends Analyzer {
    private static final int LIMIT = 6;

    private MostPopularResponseCodesAnalyzer() {
    }

    /**
     * Analyzes the given list of Nginx log entries and identifies the most popular HTTP response codes.
     * Generates a report in the form of a three-column table, displaying the response codes, their descriptions,
     * and occurrence counts.
     *
     * @param nginxLogEntries A list of Nginx log entries to be analyzed.
     * @return A formatted three-column table representing the most popular response codes, their descriptions,
     *     and occurrence counts.
     */
    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogEntries) {
        Map<String, Long> codeCount = new HashMap<>();

        for (NginxLogEntry entry : nginxLogEntries) {
            String code = Integer.toString(entry.status());
            codeCount.put(code, codeCount.getOrDefault(code, 0L) + 1L);
        }

        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(codeCount.entrySet());
        sortedEntries.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        return getTableBuilder(sortedEntries).toString();
    }

    /**
     * Builds a {@link StringBuilder} containing a three-column table based on
     * the provided list of response code entries.
     *
     * @param entries A list of map entries, where each entry contains a response code ({@link String})
     *                and its occurrence count ({@link Long}).
     * @return A {@link StringBuilder} containing the formatted three-column table based
     *     on the provided response code entries.
     */
    @NotNull
    private static StringBuilder getTableBuilder(@NotNull List<Map.Entry<String, Long>> entries) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < Math.min(LIMIT, entries.size()); i++) {
            Map.Entry<String, Long> entry = entries.get(i);

            builder.append(createThreeColumnRow(
                entry.getKey(),
                getCodeDescription(entry.getKey()),
                entry.getValue().toString()
            ));
        }

        return builder;
    }

    /**
     * Retrieves the description for a given HTTP response code.
     *
     * @param code The HTTP response code as a string.
     * @return The description of the response code, or "Unknown" if the code is not recognized.
     */
    @Contract(pure = true)
    private static String getCodeDescription(@NotNull String code) {
        return switch (code) {
            case "200" -> "OK";
            case "206" -> "Partial Content";
            case "301" -> "Moved Permanently";
            case "302" -> "Found";
            case "304" -> "Not Modified";
            case "400" -> "Bad Request";
            case "403" -> "Forbidden";
            case "404" -> "Not Found";
            case "500" -> "Internal Server Error";
            default -> "Unknown";
        };
    }
}
