package edu.project3.provider;

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

public class NginxLogsProvider {
    private static final Path LOG_FILES_PATH = Path.of("src/main/java/edu/project3/");

    private NginxLogsProvider() {
    }

    public static List<Path> getLogFiles(@NotNull PathMatcher pathMatcher) {
        try (var fileStream = Files.walk(LOG_FILES_PATH)) {
            return fileStream
                .filter(pathMatcher::matches)
                .toList();
        } catch (IOException ignored) {
            return Collections.emptyList();
        }
    }

    public static @NotNull List<String> readLogLinesFromUrl(@NotNull URL url) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try (BufferedReader urlReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
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
}
