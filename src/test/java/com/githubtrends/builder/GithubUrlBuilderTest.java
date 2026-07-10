package com.githubtrends.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.githubtrends.cli.OrderType;
import com.githubtrends.cli.SortType;

public class GithubUrlBuilderTest {
    @Test
    void buildUrlWithAllParameters() {
        String url = new GithubUrlBuilder().language("java")
                .sort(SortType.STARS)
                .order(OrderType.DESC)
                .count(10)
                .page(1)
                .build();
        assertEquals(
                "https://api.github.com/search/repositories?q=language:java&sort=stars&order=desc&per_page=10&page=1",
                url);
    }

    @Test
    void buildUrlWithDifferentLanguage() {
        String url = new GithubUrlBuilder()
                .language("python")
                .sort(SortType.STARS)
                .order(OrderType.DESC)
                .count(20)
                .page(2)
                .build();
        assertEquals(
                "https://api.github.com/search/repositories?q=language:python&sort=stars&order=desc&per_page=20&page=2",
                url);
    }

    @Test
    void buildUrlWithForkSort() {
        String url = new GithubUrlBuilder()
                .language("java")
                .sort(SortType.FORKS)
                .order(OrderType.DESC)
                .count(15)
                .page(3)
                .build();
        assertEquals(
                "https://api.github.com/search/repositories?q=language:java&sort=forks&order=desc&per_page=15&page=3",
                url);
    }

    @Test
    void buildUrlWithAscendingOrder() {
        String url = new GithubUrlBuilder()
                .language("java")
                .sort(SortType.UPDATED)
                .order(OrderType.ASC)
                .count(30)
                .page(5)
                .build();
        assertEquals(
                "https://api.github.com/search/repositories?q=language:java&sort=updated&order=asc&per_page=30&page=5",
                url);
    }

    @Test
    void buildUrlWithDifferentCountAndPage() {
        String url = new GithubUrlBuilder()
                .language("go")
                .sort(SortType.STARS)
                .order(OrderType.DESC)
                .count(100)
                .page(10)
                .build();
        assertEquals(
                "https://api.github.com/search/repositories?q=language:go&sort=stars&order=desc&per_page=100&page=10",
                url);
    }

    @Test
    void buildWithoutSettingValues() {
        String url = new GithubUrlBuilder().build();
        assertEquals(
                "https://api.github.com/search/repositories?q=language:null&sort=null&order=null&per_page=0&page=0",
                url);
    }
}
