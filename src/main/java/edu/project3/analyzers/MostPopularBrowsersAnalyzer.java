package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import static edu.project3.report.ReportGenerator.createTwoColumnRow;

public final class MostPopularBrowsersAnalyzer {
    private static final int LIMIT = 10;

    private MostPopularBrowsersAnalyzer() {
    }

    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogItems) {
        Map<String, Long> resourceCount = new HashMap<>();

        for (NginxLogEntry entry : nginxLogItems) {
            String browser = detectBrowser(entry.httpUserAgent());

            resourceCount.put(browser, resourceCount.getOrDefault(browser, 0L) + 1L);
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
    public static @NotNull String detectBrowser(@NotNull String userAgent) {
        var ua = userAgent.toLowerCase();
        String browser;

        if (ua.contains("msie") || ua.contains("trident")) {
            browser = "Internet Explorer";
        } else if (ua.contains("edge")) {
            browser = "Microsoft Edge";
        } else if (ua.contains("opr") || ua.contains("opera")) {
            browser = "Opera";
        } else if (ua.contains("chrome")) {
            browser = "Google Chrome";
        } else if (ua.contains("safari")) {
            browser = "Safari";
        } else if (ua.contains("firefox")) {
            browser = "Firefox";
        } else {
            browser = "Unknown Browser";
        }

        return browser;
    }
}
