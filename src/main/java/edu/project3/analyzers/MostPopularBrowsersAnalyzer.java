package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * An analyzer that identifies the most popular browsers based on Nginx log entries.
 * It counts the occurrences of each browser in the user-agent field and generates a report.
 */
public final class MostPopularBrowsersAnalyzer extends Analyzer {
    private static final int LIMIT = 10;

    private MostPopularBrowsersAnalyzer() {
    }

    /**
     * Analyzes the given list of Nginx log entries and identifies the most popular browsers.
     * Generates a report in the form of a two-column table, displaying the browsers and their occurrence counts.
     *
     * @param nginxLogEntries A list of Nginx log entries to be analyzed.
     * @return A formatted two-column table representing the most popular browsers and their occurrence counts.
     */
    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogEntries) {
        Map<String, Long> userAgentCount = new HashMap<>();

        for (NginxLogEntry entry : nginxLogEntries) {
            String browser = detectBrowser(entry.httpUserAgent());

            userAgentCount.put(browser, userAgentCount.getOrDefault(browser, 0L) + 1L);
        }

        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(userAgentCount.entrySet());
        sortedEntries.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        return getTwoColumnsTableBuilder(sortedEntries, LIMIT).toString();
    }

    /**
     * Detects the browser from the given user-agent string.
     *
     * @param userAgent The user-agent string from an HTTP request.
     * @return The detected browser name or "Unknown Browser" if the browser is not recognized.
     */
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
