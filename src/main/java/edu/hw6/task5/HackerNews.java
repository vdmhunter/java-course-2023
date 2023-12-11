package edu.hw6.task5;

import edu.common.Generated;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code HackerNews} class provides a simple interface to interact with the Hacker News API
 * and retrieve information about top stories and individual news items.
 */
public class HackerNews {
    private final HttpClient client;

    private static final URI HACKER_NEWS_URL = URI.create("https://hacker-news.firebaseio.com/v0/");
    private static final URI TOP_STORIES_URL = HACKER_NEWS_URL.resolve("topstories.json");
    private static final URI ITEM_URL = HACKER_NEWS_URL.resolve("item/");
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10L);

    public HackerNews(HttpClient client) {
        this.client = client;
    }

    /**
     * Retrieves an array of IDs representing the top stories on Hacker News.
     *
     * @return An array of long values representing the IDs of the top stories.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws InterruptedException If the HTTP request is interrupted.
     */
    public long[] hackerNewsTopStories() throws IOException, InterruptedException {
        HttpResponse<String> response = getResponse(
            HttpRequest.newBuilder()
                .uri(TOP_STORIES_URL)
                .timeout(DEFAULT_TIMEOUT)
                .GET()
                .build()
        );

        return parseTopStories(response);
    }

    /**
     * Retrieves the title of a Hacker News story with the specified ID.
     *
     * @param id The ID of the Hacker News story.
     * @return The title of the specified Hacker News story.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws InterruptedException If the HTTP request is interrupted.
     */
    public String news(long id) throws IOException, InterruptedException {
        HttpResponse<String> response = getResponse(
            HttpRequest.newBuilder()
                .uri(ITEM_URL.resolve(id + ".json"))
                .timeout(DEFAULT_TIMEOUT)
                .GET()
                .build()
        );

        return parseStoryTitle(response);
    }

    /**
     * Parses the response body of a Hacker News API call to retrieve an array of top story IDs.
     *
     * @param response The HTTP response containing the response body.
     * @return An array of long values representing the IDs of the top stories.
     */
    private long[] parseTopStories(@NotNull HttpResponse<String> response) {
        String responseBody = response.body();
        String[] numberStrings = responseBody.substring(1, responseBody.length() - 1).split(",");

        return Arrays.stream(numberStrings)
            .mapToLong(Long::parseLong)
            .toArray();
    }

    /**
     * Parses the response body of a Hacker News API call to retrieve the title of a story.
     *
     * @param response The HTTP response containing the response body.
     * @return The title of the Hacker News story.
     */
    private @NotNull String parseStoryTitle(@NotNull HttpResponse<String> response) {
        String responseBody = response.body();
        String regex = "\"title\":\"(.*?)\"";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(responseBody);

        String result = "";

        if (matcher.find()) {
            result = matcher.group(1);
        }

        return result;
    }

    /**
     * Sends an HTTP request using the provided {@link HttpRequest} and retrieves the response body as a string.
     *
     * @param request The HTTP request to be sent.
     * @return The HTTP response body as a string.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws InterruptedException If the HTTP request is interrupted.
     */
    @Generated
    private HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();

            throw e;
        }
    }
}
