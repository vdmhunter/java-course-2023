package edu.project3.analyzers;

import edu.project3.parser.NginxLogEntry;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import static edu.project3.report.ReportGenerator.createThreeColumnRow;

public final class MostPopularResponseCodesAnalyzer {
    private static final int LIMIT = 5;

    private MostPopularResponseCodesAnalyzer() {
    }

    public static @NotNull String analyze(@NotNull List<NginxLogEntry> nginxLogItems) {
        Map<String, String> top5codes = nginxLogItems.stream()
            .map(item -> Integer.toString(item.status()))
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

        top5codes.forEach((status, count) ->
            builder.append(
                createThreeColumnRow(status, getStatusDescription(status), count)
            )
        );

        return builder.toString();
    }

    @Contract(pure = true)
    private static String getStatusDescription(@NotNull String statusCode) {
        return switch (statusCode) {
            case "200" -> "OK";
            case "201" -> "Created";
            case "204" -> "No Content";
            case "400" -> "Bad Request";
            case "401" -> "Unauthorized";
            case "403" -> "Forbidden";
            case "404" -> "Not Found";
            case "500" -> "Internal Server Error";
            default -> "Unknown";
        };
    }
}
