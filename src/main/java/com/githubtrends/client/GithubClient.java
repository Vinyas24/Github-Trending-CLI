package com.githubtrends.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.githubtrends.model.SearchResponse;

public class GithubClient {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String BASE_URL = "https://api.github.com/search/repositories";
    private final String SORT = "stars";
    private final String ORDER = "desc";
    private final int PER_PAGE = 10;

    public SearchResponse findTrendingRepositories(String language, int count) throws IOException, InterruptedException {
        String url = BASE_URL + "?q=language:" + language + "&sort=" + SORT + "&order=" + ORDER + "&per_page="
                + count;
                System.out.println(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            SearchResponse responseJson = mapper.readValue(response.body(), SearchResponse.class);
            return responseJson;
        } else {
            throw new IOException(response.statusCode() + " Error : IOException occured.!");
        }
    }
}
