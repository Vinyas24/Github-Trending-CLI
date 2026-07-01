package com.githubtrends;

import java.io.IOException;
import com.githubtrends.client.GithubClient;
import com.githubtrends.model.Repository;
import com.githubtrends.model.SearchResponse;

public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        GithubClient gitTrend = new GithubClient();
        SearchResponse trends = gitTrend.findTrendingRepositories();
        for(Repository sr: trends.getItems()){
            System.out.println(sr.toString());
        }
    }
}
