package com.githubtrends.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GithubClient {
    private final HttpClient clientReq = HttpClient.newHttpClient();
    public String findTrendingRepositories() throws IOException,InterruptedException{
        HttpRequest request =  HttpRequest.newBuilder()
        .uri(URI.create("https://api.github.com/search/repositories?q=language:java&sort=stars&order=desc&per_page=10"))
        .GET()
        .build();
        HttpResponse<String> response = clientReq.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 200)
        return response.body();
    else
        throw new IOException(response.statusCode() + " Error : IOException occured.!");
    }
    
}
