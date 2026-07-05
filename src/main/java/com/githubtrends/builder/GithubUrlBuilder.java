package com.githubtrends.builder;

import com.githubtrends.cli.OrderType;
import com.githubtrends.cli.SortType;

public class GithubUrlBuilder {
    private String language;
    private SortType sort;
    private OrderType order;
    private int count;
    private int page;

    private static final String BASE_URL = "https://api.github.com/search/repositories";

    public String build() {
        return BASE_URL +
        "?q=language:" + language 
        + "&sort=" + sort.getApiValue() 
        + "&order=" + order.getApiValue() 
        + "&per_page="
        + count 
        + "&page=" + page;
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

    public GithubUrlBuilder count(int count) {
        this.count = count;
        return this;
    }

    public GithubUrlBuilder page(int page) {
        this.page = page;
        return this;
    }
}
