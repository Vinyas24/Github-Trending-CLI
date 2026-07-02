package com.githubtrends;

import java.io.IOException;
import com.githubtrends.client.GithubClient;
import com.githubtrends.model.Repository;
import com.githubtrends.model.SearchResponse;

public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        // String language = ;
        GithubClient gitTrend = new GithubClient();
        if(args.length == 0){
            System.out.println("Enter language in <language>");
            return;
        }
        SearchResponse trends = gitTrend.findTrendingRepositories(args[0]);
        
        System.out.println("---------------------");
        for(int i = 0; i < trends.getItems().size();i++){
            Repository response = trends.getItems().get(i);
            System.out.println("#" + (i+1) + " " + response.getName());
            System.out.println("Owner : " + response.getOwner());
            System.out.println("Language : " + response.getLanguage());
            System.out.println("Stars : " + response.getStargazersCount());
            System.out.println("Repository : " + response.getHtmlUrl());
            System.out.println("---------------------");
        }
    }
}
