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

        return switch (ua) {
            case String _ua when _ua.contains("msie") || _ua.contains("trident") -> "Internet Explorer";
            case String _ua when _ua.contains("edge") -> "Microsoft Edge";
            case String _ua when _ua.contains("opr") || _ua.contains("opera") -> "Opera";
            case String _ua when _ua.contains("chrome") -> "Google Chrome";
            case String _ua when _ua.contains("safari") -> "Safari";
            case String _ua when _ua.contains("firefox") -> "Firefox";
            default -> "Unknown Browser";
        };
    }
}
