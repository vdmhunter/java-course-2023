package edu.project3.provider.sources;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A {@code UrlSource} represents a source of Nginx log entries based on a URL.
 */
public final class UrlSource implements Source {
    private final URL url;
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructs a {@code UrlSource} with the specified URL pattern.
     *
     * @param pattern The URL pattern used to create the {@code URL} object.
     * @throws MalformedURLException If the given string representation of the URL is invalid.
     * @throws URISyntaxException    If the given string representation is not a valid URI.
     */
    public UrlSource(String pattern) throws MalformedURLException, URISyntaxException {
        URL tempUrl;

        try {
            tempUrl = new URI(pattern).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            LOGGER.error(e);

            throw e;
        }

        this.url = tempUrl;
    }

    /**
     * Gets the URL associated with this {@code UrlSource}.
     *
     * @return The {@code URL} object representing the source of Nginx log entries.
     */
    public URL getURL() {
        return url;
    }
}
