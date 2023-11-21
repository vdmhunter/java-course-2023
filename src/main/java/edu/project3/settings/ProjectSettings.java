package edu.project3.settings;

import edu.project3.provider.sources.PathSource;
import edu.project3.provider.sources.Source;
import edu.project3.provider.sources.UrlSource;
import edu.project3.report.ReportFormat;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code ProjectSettings} class represents the settings for the project, including the source of log files,
 * date range, and output format for generating reports.
 */
public class ProjectSettings {
    private Source source;
    private OffsetDateTime from;
    private OffsetDateTime to;
    private ReportFormat format;
    private static final String PATH_OPTION = "path";
    private static final String FROM_OPTION = "from";
    private static final String TO_OPTION = "to";
    private static final String FORMAT_OPTION = "format";

    /**
     * Parses the command-line arguments and sets the corresponding fields in the {@code ProjectSettings} object.
     *
     * @param args The command-line arguments.
     */
    public void parseArgs(String[] args) {
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

        source = convert(cmd.getOptionValue(PATH_OPTION));
        from = cmd.getOptionValue(FROM_OPTION) != null ? OffsetDateTime.parse(cmd.getOptionValue(FROM_OPTION)) : null;
        to = cmd.getOptionValue(TO_OPTION) != null ? OffsetDateTime.parse(cmd.getOptionValue(TO_OPTION)) : null;
        format = cmd.getOptionValue(FORMAT_OPTION) != null
                ? ReportFormat.valueOf(cmd.getOptionValue(FORMAT_OPTION).toUpperCase())
                : ReportFormat.MARKDOWN;
    }

    /**
     * Constructs and retrieves the command-line options to be used in parsing command-line arguments.
     *
     * @return The command-line options configured for the project.
     */
    @NotNull
    private static Options getOptions() {
        Options options = new Options();

        Option pathOption =
            new Option(
                "p",
                PATH_OPTION,
                true,
                "Required parameter specifies the location of the log files. Local path or URL."
            );
        pathOption.setType(Source.class);
        pathOption.setRequired(true);
        options.addOption(pathOption);

        Option fromOption = new Option(
            "f",
            FROM_OPTION,
            true,
            "Optional parameter specifies the start date in ISO-8601 format."
        );
        options.addOption(fromOption);

        Option toOption = new Option(
            "t",
            TO_OPTION,
            true,
            "Optional parameter specifies the end date in ISO-8601 format."
        );
        options.addOption(toOption);

        Option formatOption = new Option(
            "o",
            FORMAT_OPTION,
            true,
            "Optional parameter is used to specify the output format (markdown or adoc) of the report."
        );
        options.addOption(formatOption);

        return options;
    }

    /**
     * Retrieves the {@code Source} object representing the source of log files.
     *
     * @return The source of log files.
     */
    public Source getSource() {
        return source;
    }

    /**
     * Retrieves the start date for filtering log entries.
     *
     * @return The start date, or null if not specified.
     */
    public OffsetDateTime getFrom() {
        return from;
    }

    /**
     * Retrieves the end date for filtering log entries.
     *
     * @return The end date, or null if not specified.
     */
    public OffsetDateTime getTo() {
        return to;
    }

    /**
     * Retrieves the end date for filtering log entries.
     *
     * @return The end date, or null if not specified.
     */
    public ReportFormat getFormat() {
        return format;
    }

    /**
     * Converts the provided pattern into a {@link Source} object, determining whether it represents a URL
     * or a local path.
     *
     * @param pattern The pattern representing either a URL or a local path.
     * @return The corresponding {@link Source} object (UrlSource or PathSource).
     */
    @Contract("_ -> new")
    public static @NotNull Source convert(String pattern) {
        try {
            return new UrlSource(pattern);
        } catch (URISyntaxException | IllegalArgumentException | MalformedURLException ignored) {
            return new PathSource(pattern);
        }
    }
}
