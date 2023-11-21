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
import static edu.project3.report.ReportGenerator.createTwoColumnRow;

public final class MostPopularResourcesAnalyzer {
    private static final int NUMBER_OF_PARTS = 3;
    private static final int LIMIT = 5;

    private MostPopularResourcesAnalyzer() {
    }

    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogItems) {
        Map<String, Long> resourceCount = new HashMap<>();

        for (NginxLogEntry entry : nginxLogItems) {
            String resource = getResourceFromRequest(entry.request());

            if (resource != null) {
                resourceCount.put(resource, resourceCount.getOrDefault(resource, 0L) + 1L);
            }
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

    @Generated
    @Contract(pure = true)
    private static @Nullable String getResourceFromRequest(@NotNull String request) {
        String[] parts = request.split(" ");

        return (parts.length == NUMBER_OF_PARTS) ? parts[1] : null;
    }
}
