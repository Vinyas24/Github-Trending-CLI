package com.githubtrends.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.githubtrends.cli.DurationType;
import com.githubtrends.cli.OrderType;
import com.githubtrends.cli.SortType;

public class GithubUrlBuilderTest {
        @Test
        void buildUrlWithAllParameters() {
                String url = new GithubUrlBuilder().language("java")
                                .sort(SortType.STARS)
                                .order(OrderType.DESC)
                                .count(10)
                                .duration(DurationType.WEEK)
                                .page(1)
                                .build();
                LocalDate expectedDate = LocalDate.now().minusWeeks(1);
                assertEquals(
                                "https://api.github.com/search/repositories?q=language%3Ajava+created%3A%3E"
                                                + expectedDate + "&sort=stars&order=desc&per_page=10&page=1",
                                url);
        }

        @Test
        void buildUrlWithDifferentLanguage() {
                String url = new GithubUrlBuilder()
                                .language("python")
                                .sort(SortType.STARS)
                                .order(OrderType.DESC)
                                .duration(DurationType.WEEK)
                                .count(20)
                                .page(2)
                                .build();
                LocalDate expectedDate = LocalDate.now().minusWeeks(1);
                assertEquals(
                                "https://api.github.com/search/repositories?q=language%3Apython+created%3A%3E"
                                                + expectedDate+"&sort=stars&order=desc&per_page=20&page=2",
                                url);
        }

        @Test
        void buildUrlWithForkSort() {
                String url = new GithubUrlBuilder()
                                .language("java")
                                .sort(SortType.FORKS)
                                .order(OrderType.DESC)
                                .duration(DurationType.WEEK)
                                .count(15)
                                .page(3)
                                .build();
                LocalDate expectedDate = LocalDate.now().minusWeeks(1);
                assertEquals(
                                "https://api.github.com/search/repositories?q=language%3Ajava+created%3A%3E"
                                                + expectedDate + "&sort=forks&order=desc&per_page=15&page=3",
                                url);
        }

        @Test
        void buildUrlWithAscendingOrder() {
                String url = new GithubUrlBuilder()
                                .language("java")
                                .sort(SortType.UPDATED)
                                .order(OrderType.ASC)
                                .duration(DurationType.WEEK)
                                .count(30)
                                .page(5)
                                .build();
                LocalDate expectedDate = LocalDate.now().minusWeeks(1);
                assertEquals(
                                "https://api.github.com/search/repositories?q=language%3Ajava+created%3A%3E"
                                                + expectedDate + "&sort=updated&order=asc&per_page=30&page=5",
                                url);
        }

        @Test
        void buildUrlWithDifferentCountAndPage() {
                String url = new GithubUrlBuilder()
                                .language("go")
                                .sort(SortType.STARS)
                                .order(OrderType.DESC)
                                .duration(DurationType.WEEK)
                                .count(100)
                                .page(10)
                                .build();
                LocalDate expectedDate = LocalDate.now().minusWeeks(1);
                assertEquals(
                                "https://api.github.com/search/repositories?q=language%3Ago+created%3A%3E"
                                                + expectedDate + "&sort=stars&order=desc&per_page=100&page=10",
                                url);
        }
}
