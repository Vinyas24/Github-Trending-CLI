package com.githubtrends.cli;

public enum SortType {
    STARS("stars"),
    FORKS("forks"), 
    UPDATED("updated");
    
    private final String apiValue;

    SortType(String apiValue) {
        this.apiValue = apiValue;
    }

    public String getApiValue() {
        return apiValue;
    }
}
