package com.githubtrends.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.githubtrends.model.SearchResponse;

public class GithubClient {
    private final HttpClient clientReq = HttpClient.newHttpClient();

    public SearchResponse findTrendingRepositories(String language) throws IOException, InterruptedException {
        String url = "https://api.github.com/search/repositories?q=language:" + language + "&sort=stars&order=desc&per_page=20";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = clientReq.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            SearchResponse responseJson = mapper.readValue(response.body(), SearchResponse.class);
            return responseJson;
        } else {
            throw new IOException(response.statusCode() + " Error : IOException occured.!");
        }
    }
}
