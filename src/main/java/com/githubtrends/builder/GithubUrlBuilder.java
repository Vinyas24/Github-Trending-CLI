package com.githubtrends.builder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import com.githubtrends.cli.DurationType;
import com.githubtrends.cli.OrderType;
import com.githubtrends.cli.SortType;

public class GithubUrlBuilder {
    private String language;
    private SortType sort;
    private OrderType order;
    private DurationType duration = DurationType.WEEK;
    private int count;
    private int page;

    private static final String BASE_URL = "https://api.github.com/search/repositories";

    public String build() {
        String query = "language:" + language + getCreatedField();
        return BASE_URL + "?q="
                + URLEncoder.encode(query, StandardCharsets.UTF_8)
                + "&sort=" + sort.getApiValue()
                + "&order=" + order.getApiValue()
                + "&per_page=" + count
                + "&page=" + page;
    }

    private String getCreatedField() {
        LocalDate date = LocalDate.now();
        switch (duration) {
            case DAY:
                date = date.minusDays(1);
                break;
            case WEEK:
                date = date.minusWeeks(1);
                break;
            case MONTH:
                date = date.minusMonths(1);
                break;
            case YEAR:
                date = date.minusYears(1);
                break;
        }
        return " created:>" + date;
    }

    public GithubUrlBuilder language(String language) {
        this.language = language;
        return this;
    }

    public GithubUrlBuilder sort(SortType sort) {
        this.sort = sort;
        return this;
    }

    public GithubUrlBuilder order(OrderType order) {
        this.order = order;
        return this;
    }

    public GithubUrlBuilder duration(DurationType duration) {
        this.duration = duration;
        return this;
    }

    public GithubUrlBuilder count(int count) {
        this.count = count;
        return this;
    }

    public GithubUrlBuilder page(int page) {
        this.page = page;
        return this;
    }
}
