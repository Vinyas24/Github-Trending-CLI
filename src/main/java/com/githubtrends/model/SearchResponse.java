package com.githubtrends.model;

import java.util.List;

public class SearchResponse {
    private List<Repository> items;

    public List<Repository> getItems() {
        return items;
    }

    public void setItems(List<Repository> items) {
        this.items = items;
    }
    
}
