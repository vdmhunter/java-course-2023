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

public class ProjectSettings {
    private Source source;
    private OffsetDateTime from;
    private OffsetDateTime to;
    private ReportFormat format;

    public void parseArgs(String[] args) {
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

        // CHECKSTYLE:OFF: Disable MultipleStringLiterals check
        source = convert(cmd.getOptionValue("path"));
        from = cmd.getOptionValue("from") != null ? OffsetDateTime.parse(cmd.getOptionValue("from")) : null;
        to = cmd.getOptionValue("to") != null ? OffsetDateTime.parse(cmd.getOptionValue("to")) : null;
        format = cmd.getOptionValue("format") != null
                ? ReportFormat.valueOf(cmd.getOptionValue("format").toUpperCase())
                : ReportFormat.MARKDOWN;
        // CHECKSTYLE:ON: Disable MultipleStringLiterals check
    }

    @NotNull
    private static Options getOptions() {
        Options options = new Options();

        Option pathOption =
            new Option(
                "p",
                "path",
                true,
                "Path to one or more NGINX log files in form of a local template or URL."
            );
        pathOption.setType(Source.class);
        pathOption.setRequired(true);
        options.addOption(pathOption);

        Option fromOption = new Option(
            "f",
            "from",
            true,
            "Optional time parameter from in ISO-8601 format, such as '2000-02-22T00:00:00+00:00'."
        );
        options.addOption(fromOption);

        Option toOption = new Option(
            "t",
            "to",
            true,
            "Optional time parameter to in ISO-8601 format, , such as '2000-02-22T00:00:00+00:00'."
        );
        options.addOption(toOption);

        Option formatOption = new Option(
            "o",
            "format",
            true,
            "Optional argument of output format of result."
        );
        options.addOption(formatOption);

        return options;
    }

    public Source getSource() {
        return source;
    }

    public OffsetDateTime getFrom() {
        return from;
    }

    public OffsetDateTime getTo() {
        return to;
    }

    public ReportFormat getFormat() {
        return format;
    }

    @Contract("_ -> new")
    public static @NotNull Source convert(String pattern) {
        try {
            return new UrlSource(pattern);
        } catch (URISyntaxException | IllegalArgumentException | MalformedURLException ignored) {
            return new PathSource(pattern);
        }
    }
}
