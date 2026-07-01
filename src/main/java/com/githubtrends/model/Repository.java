package com.githubtrends.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
    private String name;
    private String description;
    private String language;
    @JsonProperty("stargazers_count")
    private long stargazersCount;
    @JsonProperty("html_url")
    private String htmlUrl;
    private Owner owner;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public long getStargazersCount() {
        return stargazersCount;
    }
    public void setStargazersCount(long stargazersCount) {
        this.stargazersCount = stargazersCount;
    }
    public String getHtmlUrl() {
        return htmlUrl;
    }
    @Override
    public String toString() {
        return "Repository [name=" + name + ", description=" + description + ", language=" + language
                + ", stargazersCount=" + stargazersCount + ", htmlUrl=" + htmlUrl + ", owner=" + owner + "]";
    }
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    
}
