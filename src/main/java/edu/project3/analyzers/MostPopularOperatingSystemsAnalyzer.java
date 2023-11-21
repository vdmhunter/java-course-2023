package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * An analyzer that identifies the most popular operating systems based on Nginx log entries.
 * It counts the occurrences of each operating system in the user-agent field and generates a report.
 */
public final class MostPopularOperatingSystemsAnalyzer extends Analyzer {
    private static final int LIMIT = 10;

    private MostPopularOperatingSystemsAnalyzer() {
    }

    /**
     * Analyzes the given list of Nginx log entries and identifies the most popular operating systems.
     * Generates a report in the form of a two-column table, displaying the operating systems
     * and their occurrence counts.
     *
     * @param nginxLogItems A list of Nginx log entries to be analyzed.
     * @return A formatted two-column table representing the most popular operating systems and their occurrence counts.
     */
    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogItems) {
        Map<String, Long> userAgentCount = new HashMap<>();

        for (NginxLogEntry entry : nginxLogItems) {
            String os = detectOperatingSystem(entry.httpUserAgent());

            userAgentCount.put(os, userAgentCount.getOrDefault(os, 0L) + 1L);
        }

        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(userAgentCount.entrySet());
        sortedEntries.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        return getTwoColumnsTableBuilder(sortedEntries, LIMIT).toString();
    }

    /**
     * Detects the operating system from the given user-agent string.
     *
     * @param userAgent The user-agent string from an HTTP request.
     * @return The detected operating system name or "Unknown Operating System"
     *     if the operating system is not recognized.
     */
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
