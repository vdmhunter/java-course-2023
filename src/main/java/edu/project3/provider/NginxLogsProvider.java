package edu.project3.provider;

import edu.common.Generated;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code NginxLogsProvider} class provides utility methods for obtaining Nginx log entries from various sources.
 */
public final class NginxLogsProvider {
    private static final Path LOG_FILES_PATH = Path.of("src/main/resources/project3/");

    private NginxLogsProvider() {
    }

    /**
     * Retrieves a list of log files that match the specified {@link PathMatcher}.
     *
     * @param pathMatcher The {@link PathMatcher} used to filter log files based on a specified pattern.
     * @return A list of {@link Path} objects representing the matched log files.
     */
    public static @NotNull List<Path> getLogFiles(@NotNull PathMatcher pathMatcher) {
        return getPaths(pathMatcher);
    }

    /**
     * Reads log lines from a specified URL using HTTP connection.
     *
     * @param url The URL from which to read log lines.
     * @return A list of log lines obtained from the specified URL.
     */
    public static @NotNull List<String> readLogLinesFromUrl(@NotNull URL url) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try (var urlReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                List<String> logLines = new ArrayList<>();

                String logLine;

                while ((logLine = urlReader.readLine()) != null) {
                    logLines.add(logLine);
                }

                return logLines;
            }
        } catch (IOException ignored) {
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves a list of file paths that match the specified {@link PathMatcher}.
     *
     * @param pathMatcher The {@link PathMatcher} used to filter file paths based on a specified pattern.
     * @return A list of {@link Path} objects representing the matched file paths.
     */
    @NotNull
    @Generated
    private static List<Path> getPaths(@NotNull PathMatcher pathMatcher) {
        try (var fileStream = Files.walk(LOG_FILES_PATH)) {
            return fileStream
                .filter(pathMatcher::matches)
                .toList();
        } catch (IOException ignored) {
            return Collections.emptyList();
        }
    }
}
