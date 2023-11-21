package edu.project3.parser;

import edu.common.Generated;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class NginxLogParser {
    private static final String IP_ADDRESS_PATTERN = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH
    );
    @SuppressWarnings("RegExpRedundantEscape")
    private static final String QUOTE = "\\\"";
    private static final Pattern LOG_ITEM_PATTERN = Pattern.compile(
        "(?<remoteAddress>" + IP_ADDRESS_PATTERN + ")" + " - "
            + "(?<remoteUser>.*)" + " "
            + "\\[" + "(?<timeLocal>.*)" + "]" + " "
            + QUOTE + "(?<request>.*)" + QUOTE + " "
            + "(?<status>\\d+)" + " "
            + "(?<bodyBytesSent>\\d+)" + " "
            + QUOTE + "(?<httpReferer>.*)" + QUOTE + " "
            + QUOTE + "(?<httpUserAgent>.*)" + QUOTE
    );

    private NginxLogParser() {
    }

    public static @NotNull List<NginxLogEntry> parseLogLines(List<String> lines) {
        return parse(lines);
    }

    public static @NotNull List<NginxLogEntry> parseLogFiles(List<Path> paths) {
        return parse(readAllFiles(paths));
    }

    @Generated
    private static @NotNull List<String> readAllFiles(@NotNull List<Path> paths) {
        List<String> lines = new ArrayList<>();

        try {
            for (var path : paths) {
                lines.addAll(Files.readAllLines(path));
            }
        } catch (IOException e) {
            return Collections.emptyList();
        }

        return lines;
    }

    @Generated
    private static @NotNull List<NginxLogEntry> parse(@NotNull List<String> lines) {
        List<NginxLogEntry> logEntries = new ArrayList<>(lines.size());

        try {
            for (var line : lines) {
                Matcher matcher = LOG_ITEM_PATTERN.matcher(line);

                if (matcher.matches()) {
                    NginxLogEntry logEntry = new NginxLogEntry(
                        InetAddress.getByName(matcher.group("remoteAddress")),
                        matcher.group("remoteUser"),
                        OffsetDateTime.parse(matcher.group("timeLocal"), DATE_TIME_FORMATTER),
                        matcher.group("request"),
                        Integer.parseInt(matcher.group("status")),
                        Long.parseLong(matcher.group("bodyBytesSent")),
                        URI.create(matcher.group("httpReferer")),
                        matcher.group("httpUserAgent")
                    );

                    logEntries.add(logEntry);
                }
            }
        } catch (UnknownHostException e) {
            return Collections.emptyList();
        }

        return logEntries;
    }
}
