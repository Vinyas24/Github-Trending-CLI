package com.githubtrends.cli;

public enum OrderType {
    DESC("desc"),
    ASC("asc");

    private final String orderValue;

    private OrderType(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getApiValue() {
        return orderValue;
    }
    
}
