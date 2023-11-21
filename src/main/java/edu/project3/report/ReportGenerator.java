package edu.project3.report;

import java.util.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code ReportGenerator} class provides static methods for generating reports in various formats,
 * such as Markdown and AsciiDoc, and creating structured tables with specified column data.
 */
public final class ReportGenerator {
    private static final String COLUMN_SEPARATOR = ">-<";
    private static final String ROW_SEPARATOR = System.lineSeparator();
    private static final String MARKDOWN_SECTION_TITLE = "#### ";
    private static final String ADOC_SECTION_TITLE = ".";
    private static final String ADOC_TABLE_SIGNATURE = "|===";

    private ReportGenerator() {
    }

    /**
     * Generates a report based on the provided table data and specified format.
     *
     * @param tableData The list of tables represented as strings.
     * @param format    The desired format for the generated report (Markdown or AsciiDoc).
     * @return A {@link Report} object containing the generated report content.
     */
    public static Report generateReport(List<String> tableData, @NotNull ReportFormat format) {
        return switch (format) {
            case ADOC -> generateAdocReport(tableData);
            case MARKDOWN -> generateMarkdownReport(tableData);
        };
    }

    /**
     * Creates a section title with the given header text.
     *
     * @param header The text to be used as the section title.
     * @return A string representing the formatted section title.
     */
    @Contract(pure = true)
    public static @NotNull String createSectionTitle(String header) {
        return header
            + ROW_SEPARATOR;
    }

    /**
     * Creates a row with two columns of data.
     *
     * @param column1Data The data for the first column.
     * @param column2Data The data for the second column.
     * @return A string representing the formatted row with two columns.
     */
    @Contract(pure = true)
    public static @NotNull String createTwoColumnRow(String column1Data, String column2Data) {
        return column1Data
            + COLUMN_SEPARATOR
            + column2Data
            + ROW_SEPARATOR;
    }

    /**
     * Creates a row with three columns of data.
     *
     * @param column1Data The data for the first column.
     * @param column2Data The data for the second column.
     * @param column3Data The data for the third column.
     * @return A string representing the formatted row with three columns.
     */
    @Contract(pure = true)
    public static @NotNull String createThreeColumnRow(String column1Data, String column2Data, String column3Data) {
        return column1Data
            + COLUMN_SEPARATOR
            + column2Data
            + COLUMN_SEPARATOR
            + column3Data
            + ROW_SEPARATOR;
    }

    /**
     * Generates a report in Markdown format based on the provided table data.
     *
     * @param tableData The list of tables represented as strings.
     * @return A {@link Report} object containing the generated Markdown report content.
     */
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

    /**
     * Generates a report in AsciiDoc format based on the provided table data.
     *
     * @param tableData The list of tables represented as strings.
     * @return A {@link Report} object containing the generated AsciiDoc report content.
     */
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

    /**
     * Creates a formatted header for a Markdown table.
     *
     * @param header The header row for the table.
     * @return A string representing the formatted Markdown header row.
     */
    private static @NotNull String createMarkdownHeader(@NotNull String header) {
        int columnCount = header.split(COLUMN_SEPARATOR).length;

        return createMarkdownRow(header)
            + "|" + ":-:|".repeat(columnCount) + ROW_SEPARATOR;
    }

    /**
     * Creates a formatted row for a Markdown table.
     *
     * @param row The data row for the table.
     * @return A string representing the formatted Markdown data row.
     */
    private static @NotNull String createMarkdownRow(@NotNull String row) {
        String[] columns = row.split(COLUMN_SEPARATOR);

        return "|" + String.join("|", columns) + "|" + ROW_SEPARATOR;
    }

    /**
     * Creates a formatted header for an AsciiDoc table.
     *
     * @param header The header row for the table.
     * @return A string representing the formatted AsciiDoc header row.
     */
    private static @NotNull String createAdocHeader(@NotNull String header) {
        return "[cols=\"^" + ",^".repeat(header.split(COLUMN_SEPARATOR).length - 1) + "\"]" + ROW_SEPARATOR
            + ADOC_TABLE_SIGNATURE + ROW_SEPARATOR
            + createAdocRow(header)
            + ROW_SEPARATOR;
    }

    /**
     * Creates a formatted row for an AsciiDoc table.
     *
     * @param row The data row for the table.
     * @return A string representing the formatted AsciiDoc data row.
     */
    private static @NotNull String createAdocRow(@NotNull String row) {
        return "|" + String.join("|", row.split(COLUMN_SEPARATOR)) + ROW_SEPARATOR;
    }

    /**
     * Creates a separator string used between tables in the report.
     *
     * @return A string representing the separator between tables.
     */
    @Contract(pure = true)
    private static @NotNull String createSeparator() {
        return ROW_SEPARATOR + ROW_SEPARATOR;
    }
}
