package edu.project3.analyzers;

import java.util.List;
import java.util.Map;
import edu.common.Generated;
import org.jetbrains.annotations.NotNull;
import static edu.project3.report.ReportGenerator.createTwoColumnRow;

/**
 * The base class for analyzers.
 * Analyzers are used to process and analyze data and generating reports.
 */
abstract class Analyzer {
    @Generated
    Analyzer() {
    }

    /**
     * Generates a {@link StringBuilder} containing a two-column table based on the provided list of entries
     * and a specified limit.
     * Each entry in the list is represented as a row in the table, with the key and value displayed in two columns.
     *
     * @param entries A list of map entries, where each entry contains a key (String) and a value (Long).
     * @param limit   The maximum number of entries to include in the table.
     * @return A {@link StringBuilder} containing the formatted two-column table based on the provided entries.
     */
    @NotNull
    static StringBuilder getTwoColumnsTableBuilder(@NotNull List<Map.Entry<String, Long>> entries, int limit) {
        var builder = new StringBuilder();

        for (int i = 0; i < Math.min(limit, entries.size()); i++) {
            Map.Entry<String, Long> entry = entries.get(i);
            builder.append(createTwoColumnRow(entry.getKey(), entry.getValue().toString()));
        }

        return builder;
    }
}
