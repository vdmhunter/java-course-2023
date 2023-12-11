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

        return switch (ua) {
            case String _ua when _ua.contains("win") -> "Windows";
            case String _ua when _ua.contains("android") -> "Android";
            case String _ua when _ua.contains("iphone") -> "iOS";
            case String _ua when _ua.contains("mac") -> "Mac";
            case String _ua when _ua.contains("linux") -> "Linux";
            case String _ua when _ua.contains("x11") || _ua.contains("unix") -> "Unix";
            case String _ua when _ua.contains("debian") -> "Debian";
            default -> "Unknown Operating System";
        };
    }
}
