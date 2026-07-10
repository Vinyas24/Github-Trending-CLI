package com.githubtrends.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.githubtrends.builder.GithubUrlBuilder;
import com.githubtrends.cli.OrderType;
import com.githubtrends.cli.SortType;
import com.githubtrends.exceptions.GithubApiException;
import com.githubtrends.logging.LoggerConfig;
import com.githubtrends.model.SearchResponse;

public class GithubClient {
    private final HttpClient client = HttpClient.newHttpClient();
    private static final Logger LOGGER = LoggerConfig.getLogger();
    private final ObjectMapper mapper = new ObjectMapper();

    public SearchResponse findTrendingRepositories(String language, int count, SortType sort, OrderType order, int page)
            throws IOException, InterruptedException {
        String url = new GithubUrlBuilder()
                .language(language)
                .count(count)
                .sort(sort)
                .order(order)
                .page(page)
                .build();
        LOGGER.info("Sending request to GitHub API.");
        LOGGER.info("Request URL: " + url);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

        long startTime = System.currentTimeMillis();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        long endTime = System.currentTimeMillis();
        LOGGER.info("GitHub API request completed in " + (endTime - startTime) + " ms.");

        int statusCode = response.statusCode();
        LOGGER.info("Response status: " + statusCode);
        if (statusCode == 200) {
            LOGGER.info("Successfully received response from GitHub.");
            return mapper.readValue(response.body(), SearchResponse.class);
        } else if (statusCode == 403) {
            LOGGER.severe("GitHub rate limit exceeded.");
            throw new GithubApiException("GitHub rate limit exceeded.");
        } else if (statusCode == 401) {
            LOGGER.severe("Authentication failed.");
            throw new GithubApiException("Authentication failed.");
        } else if (statusCode == 404) {
            LOGGER.severe("GitHub resource not found.");
            throw new GithubApiException("GitHub resource not found.");
        } else if (statusCode == 500) {
            LOGGER.severe("GitHub server error.");
            throw new GithubApiException("GitHub server error.");
        } else {
            LOGGER.severe("GitHub API returned unexpected status code: " + statusCode);
            throw new GithubApiException("GitHub API returned unexpected status code: " + statusCode);
        }
    }
}
