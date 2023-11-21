package edu.project3.report;

import java.util.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class ReportGenerator {
    private static final String COLUMN_SEPARATOR = ">-<";
    private static final String ROW_SEPARATOR = System.lineSeparator();
    private static final String MARKDOWN_SECTION_TITLE = "#### ";
    private static final String ADOC_SECTION_TITLE = ".";
    private static final String ADOC_TABLE_SIGNATURE = "|===";

    private ReportGenerator() {
    }

    public static Report generateReport(List<String> tableData, @NotNull ReportFormat format) {
        return switch (format) {
            case ADOC -> generateAdocReport(tableData);
            case MARKDOWN -> generateMarkdownReport(tableData);
        };
    }

    @Contract(pure = true)
    public static @NotNull String createSectionTitle(String header) {
        return header
            + ROW_SEPARATOR;
    }

    @Contract(pure = true)
    public static @NotNull String createTwoColumnRow(String column1Data, String column2Data) {
        return column1Data
            + COLUMN_SEPARATOR
            + column2Data
            + ROW_SEPARATOR;
    }

    @Contract(pure = true)
    public static @NotNull String createThreeColumnRow(String column1Data, String column2Data, String column3Data) {
        return column1Data
            + COLUMN_SEPARATOR
            + column2Data
            + COLUMN_SEPARATOR
            + column3Data
            + ROW_SEPARATOR;
    }

    @Contract("_ -> new")
    private static @NotNull Report generateMarkdownReport(@NotNull List<String> tableData) {
        var reportBuilder = new StringBuilder();

        for (String table : tableData) {
            String[] rows = table.split(ROW_SEPARATOR);
            reportBuilder.append(MARKDOWN_SECTION_TITLE);
            reportBuilder.append(rows[0]);
            reportBuilder.append(createSeparator());
            reportBuilder.append(createMarkdownHeader(rows[1]));

            for (int rowIndex = 2; rowIndex < rows.length; rowIndex++) {
                reportBuilder.append(createMarkdownRow(rows[rowIndex]));
            }

            reportBuilder.append(ROW_SEPARATOR);
        }

        return new Report(reportBuilder.toString());
    }

    @Contract("_ -> new")
    private static @NotNull Report generateAdocReport(@NotNull List<String> tableData) {
        var reportBuilder = new StringBuilder();

        for (String table : tableData) {
            String[] rows = table.split(ROW_SEPARATOR);
            reportBuilder.append(ADOC_SECTION_TITLE);
            reportBuilder.append(rows[0]);
            reportBuilder.append(createSeparator());
            reportBuilder.append(createAdocHeader(rows[1]));

            for (int rowIndex = 2; rowIndex < rows.length; ++rowIndex) {
                reportBuilder.append(createAdocRow(rows[rowIndex]));
            }

            reportBuilder.append(ADOC_TABLE_SIGNATURE).append(ROW_SEPARATOR);
        }

        return new Report(reportBuilder.toString());
    }

    private static @NotNull String createMarkdownHeader(@NotNull String header) {
        int columnCount = header.split(COLUMN_SEPARATOR).length;

        return createMarkdownRow(header)
            + "|" + ":-:|".repeat(columnCount) + ROW_SEPARATOR;
    }

    private static @NotNull String createMarkdownRow(@NotNull String row) {
        String[] columns = row.split(COLUMN_SEPARATOR);

        return "|" + String.join("|", columns) + "|" + ROW_SEPARATOR;
    }

    private static @NotNull String createAdocHeader(@NotNull String header) {
        return "[cols=\"^" + ",^".repeat(header.split(COLUMN_SEPARATOR).length - 1) + "\"]" + ROW_SEPARATOR
            + ADOC_TABLE_SIGNATURE + ROW_SEPARATOR
            + createAdocRow(header)
            + ROW_SEPARATOR;
    }

    private static @NotNull String createAdocRow(@NotNull String row) {
        return "|" + String.join("|", row.split(COLUMN_SEPARATOR)) + ROW_SEPARATOR;
    }

    @Contract(pure = true)
    private static @NotNull String createSeparator() {
        return ROW_SEPARATOR + ROW_SEPARATOR;
    }
}
