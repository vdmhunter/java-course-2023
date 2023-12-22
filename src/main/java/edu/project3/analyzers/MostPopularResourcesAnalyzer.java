package edu.project3.analyzers;

import edu.common.Generated;
import edu.project3.parser.NginxLogEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An analyzer that identifies the most popular resources based on Nginx log entries.
 * It counts the occurrences of each resource in the request field and generates a report.
 */
public final class MostPopularResourcesAnalyzer extends Analyzer {
    private static final int NUMBER_OF_PARTS = 3;
    private static final int LIMIT = 5;

    private MostPopularResourcesAnalyzer() {
    }

    /**
     * Analyzes the given list of Nginx log entries and identifies the most popular resources.
     * Generates a report in the form of a two-column table, displaying the resources and their occurrence counts.
     *
     * @param nginxLogEntries A list of Nginx log entries to be analyzed.
     * @return A formatted two-column table representing the most popular resources and their occurrence counts.
     */
    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogEntries) {
        Map<String, Long> resourceCount = new HashMap<>();

        for (NginxLogEntry entry : nginxLogEntries) {
            String resource = getResourceFromRequest(entry.request());

            if (resource != null) {
                resourceCount.put(resource, resourceCount.getOrDefault(resource, 0L) + 1L);
            }
        }

        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(resourceCount.entrySet());
        sortedEntries.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        return getTwoColumnsTableBuilder(sortedEntries, LIMIT).toString();
    }

    /**
     * Extracts the resource from the given request string.
     *
     * @param request The request string containing information about the resource.
     * @return The extracted resource or null if the request does not contain the expected number of parts.
     */
    @Generated
    @Contract(pure = true)
    private static @Nullable String getResourceFromRequest(@NotNull String request) {
        String[] parts = request.split(" ");

        return (parts.length == NUMBER_OF_PARTS)
            ? parts[1]
            : null;
    }
}
