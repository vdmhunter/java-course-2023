package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import static edu.project3.report.ReportGenerator.createTwoColumnRow;

public final class MostPopularOperationSystemsAnalyzer {
    private static final int LIMIT = 10;

    private MostPopularOperationSystemsAnalyzer() {
    }

    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogItems) {
        Map<String, Long> resourceCount = new HashMap<>();

        for (NginxLogEntry entry : nginxLogItems) {
            String os = detectOperatingSystem(entry.httpUserAgent());

            resourceCount.put(os, resourceCount.getOrDefault(os, 0L) + 1L);
        }

        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(resourceCount.entrySet());
        sortedEntries.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        return getTableData(sortedEntries).toString();
    }

    @NotNull
    private static StringBuilder getTableData(@NotNull List<Map.Entry<String, Long>> entries) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < Math.min(LIMIT, entries.size()); i++) {
            Map.Entry<String, Long> entry = entries.get(i);
            builder.append(createTwoColumnRow(entry.getKey(), entry.getValue().toString()));
        }

        return builder;
    }

    @Contract(pure = true)
    public static @NotNull String detectOperatingSystem(@NotNull String userAgent) {
        var ua = userAgent.toLowerCase();
        String os;

        if (ua.contains("win")) {
            os = "Windows";
        } else if (ua.contains("android")) {
            os = "Android";
        } else if (ua.contains("iphone")) {
            os = "iOS";
        } else if (ua.contains("mac")) {
            os = "Mac";
        } else if (ua.contains("linux")) {
            os = "Linux";
        } else if (ua.contains("x11") || ua.contains("unix")) {
            os = "Unix";
        } else if (ua.contains("debian")) {
            os = "Debian";
        } else {
            os = "Unknown Operating System";
        }

        return os;
    }
}
