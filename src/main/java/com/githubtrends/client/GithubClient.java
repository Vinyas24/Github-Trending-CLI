package com.githubtrends.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.githubtrends.cli.OrderType;
import com.githubtrends.cli.SortType;
import com.githubtrends.model.SearchResponse;

public class GithubClient {
    private final HttpClient client = HttpClient.newHttpClient();
    private static final String BASE_URL = "https://api.github.com/search/repositories";
    // private final String SORT = "stars";
    // private final String ORDER = "desc";

    private final ObjectMapper mapper = new ObjectMapper();
    public SearchResponse findTrendingRepositories(String language, int count, SortType sort, OrderType order, int page) throws IOException, InterruptedException {
        String url = BASE_URL + "?q=language:" + language + "&sort=" + sort.getApiValue() + "&order=" + order.getApiValue() + "&per_page="
                + count + "&page=" + page;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), SearchResponse.class);
        } else {
            throw new IOException(response.statusCode() + " Error : IOException occured.!");
        }
    }
}
