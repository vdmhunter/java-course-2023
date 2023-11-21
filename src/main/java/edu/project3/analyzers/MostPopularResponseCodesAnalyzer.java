package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import static edu.project3.report.ReportGenerator.createThreeColumnRow;

public final class MostPopularResponseCodesAnalyzer {
    private static final int LIMIT = 6;

    private MostPopularResponseCodesAnalyzer() {
    }

    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogItems) {
        Map<String, Long> codeCount = new HashMap<>();

        for (NginxLogEntry entry : nginxLogItems) {
            String code = Integer.toString(entry.status());
            codeCount.put(code, codeCount.getOrDefault(code, 0L) + 1L);
        }

        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(codeCount.entrySet());
        sortedEntries.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        return getTableData(sortedEntries).toString();
    }

    @NotNull
    private static StringBuilder getTableData(@NotNull List<Map.Entry<String, Long>> entries) {
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
