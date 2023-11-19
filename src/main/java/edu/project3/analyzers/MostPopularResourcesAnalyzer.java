package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static edu.project3.report.ReportGenerator.createTwoColumnRow;

public final class MostPopularResourcesAnalyzer {

    private static final int NUMBER_OF_TOKENS = 3;
    private static final int LIMIT = 5;

    private MostPopularResourcesAnalyzer() {
    }

    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogItems) {
        Map<String, String> top5resources = nginxLogItems.stream()
            .map(NginxLogEntry::request)
            .map(MostPopularResourcesAnalyzer::getResourceFromRequest)
            .collect(
                Collectors.groupingBy(
                    Function.identity(),
                    Collectors.counting()
                )
            )
            .entrySet()
            .stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(LIMIT)
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry.getValue().toString(),
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                )
            );

        var builder = new StringBuilder();

        top5resources.forEach((resource, count) ->
            builder.append(createTwoColumnRow(resource, count))
        );

        return builder.toString();
    }

    @Contract(pure = true)
    private static @Nullable String getResourceFromRequest(@NotNull String request) {
        String[] tokens = request.split(" ");

        return (tokens.length == NUMBER_OF_TOKENS)
            ? tokens[1]
            : null;
    }
}
