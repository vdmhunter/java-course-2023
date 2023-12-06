package edu.hw6;

import edu.hw6.task5.HackerNews;
import java.io.IOException;
import java.net.http.HttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 6, Task 5
 */
class Task5Test {
    private final HttpClient client = HttpClient.newHttpClient();

    @Test
    @DisplayName("Test HackerNews Top Stories Retrieval")
    void hackerNews_TestTopStories() throws IOException, InterruptedException {
        // Arrange
        HackerNews hackerNews = new HackerNews(client);

        // Act
        long[] ids = hackerNews.hackerNewsTopStories();
        boolean actual = ids.length > 0;

        // Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test retrieval of HackerNews news by ID")
    void hackerNews_TestNews() throws IOException, InterruptedException {
        // Arrange
        HackerNews hackerNews = new HackerNews(client);
        String expected = "JDK 21 Release Notes";

        // Act
        String actual = hackerNews.news(37570037);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
