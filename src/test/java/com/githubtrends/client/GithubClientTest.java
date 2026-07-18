package com.githubtrends.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;

import com.githubtrends.cli.DurationType;
import com.githubtrends.cli.OrderType;
import com.githubtrends.cli.SortType;
import com.githubtrends.exceptions.GithubApiException;
import com.githubtrends.model.SearchResponse;

public class GithubClientTest {
    @Test
    void returnRepositoriesWhenApiReturns200() throws IOException, InterruptedException {
        HttpClient client = mock(HttpClient.class);
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(200);
        when(response.body()).thenReturn("{\"items\":[]}");
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(response);
        GithubClient githubClient = new GithubClient(client);
        SearchResponse searchResponse = githubClient.findTrendingRepositories(
                "java",
                10,
                SortType.STARS,
                OrderType.DESC,
                DurationType.WEEK,
                1);
        assertNotNull(searchResponse);
        assertNotNull(searchResponse.getItems());
        assertEquals(0, searchResponse.getItems().size());
        verify(client)
                .send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void throwExceptionWhenApiReturns401() throws IOException, InterruptedException {
        HttpClient client = mock(HttpClient.class);
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(401);
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);
        GithubClient githubClient = new GithubClient(client);
        GithubApiException exception = assertThrows(
                GithubApiException.class,
                () -> githubClient.findTrendingRepositories(
                        "java",
                        10,
                        SortType.STARS,
                        OrderType.DESC,
                        DurationType.WEEK,
                        1));

        assertEquals("Authentication failed.", exception.getMessage());
        verify(client).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void throwExceptionWhenApiReturns403() throws IOException, InterruptedException {
        HttpClient client = mock(HttpClient.class);
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(403);
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);
        GithubClient githubClient = new GithubClient(client);
        GithubApiException exception = assertThrows(
                GithubApiException.class,
                () -> githubClient.findTrendingRepositories(
                        "java",
                        10,
                        SortType.STARS,
                        OrderType.DESC,
                        DurationType.WEEK,
                        1));
        assertEquals("GitHub rate limit exceeded.", exception.getMessage());
        verify(client).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void throwExceptionWhenApiReturns404() throws IOException, InterruptedException {
        HttpClient client = mock(HttpClient.class);
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(404);
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);
        GithubClient githubClient = new GithubClient(client);
        GithubApiException exception = assertThrows(
                GithubApiException.class,
                () -> githubClient.findTrendingRepositories(
                        "java",
                        10,
                        SortType.STARS,
                        OrderType.DESC,
                        DurationType.WEEK,
                        1));
        assertEquals("GitHub resource not found.", exception.getMessage());
        verify(client).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void throwExceptionWhenApiReturns500() throws IOException, InterruptedException {
        HttpClient client = mock(HttpClient.class);
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(500);
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);
        GithubClient githubClient = new GithubClient(client);
        GithubApiException exception = assertThrows(
                GithubApiException.class,
                () -> githubClient.findTrendingRepositories(
                        "java",
                        10,
                        SortType.STARS,
                        OrderType.DESC,
                        DurationType.WEEK,
                        1));
        assertEquals("GitHub server error.", exception.getMessage());
        verify(client).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void throwExceptionWhenApiReturnsUnexpectedStatusCode() throws IOException, InterruptedException {
        HttpClient client = mock(HttpClient.class);
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(418);
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);
        GithubClient githubClient = new GithubClient(client);
        GithubApiException exception = assertThrows(
                GithubApiException.class,
                () -> githubClient.findTrendingRepositories(
                        "java",
                        10,
                        SortType.STARS,
                        OrderType.DESC,
                        DurationType.WEEK,
                        1));
        assertEquals(
                "GitHub API returned unexpected status code: 418",
                exception.getMessage());
        verify(client).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }
}
