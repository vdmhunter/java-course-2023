package edu.project3.provider.sources;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class UrlSource implements Source {

    private final URL url;
    private static final Logger LOGGER = LogManager.getLogger();

    public UrlSource(String pattern) throws MalformedURLException, URISyntaxException {
        URL tempUrl = null;

        try {
            tempUrl = new URI(pattern).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            LOGGER.error(e);

            throw e;
        }

        this.url = tempUrl;
    }

    public URL getURL() {
        return url;
    }
}
