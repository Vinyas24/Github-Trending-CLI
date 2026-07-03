package com.githubtrends;

import java.io.IOException;
import com.githubtrends.client.GithubClient;
import com.githubtrends.model.Repository;
import com.githubtrends.model.SearchResponse;

public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        
        GithubClient githubTrend = new GithubClient();
        
        if(args.length <= 1){
            System.out.println("Usage : java app <language> <repository-count>");
            return;
        }
        int count = 0;
        try{
            count = Integer.parseInt(args[1]);
            if(count <= 0) {
                System.out.println("Enter positive number");
                return;
            }
        }
        catch(NumberFormatException e){
            System.out.println("Enter valid number for <repository-count>");
            return;
        }
        SearchResponse trends = githubTrend.findTrendingRepositories(args[0], count);
        
        System.out.println("============================");
        for(int i = 0; i < trends.getItems().size();i++){
            Repository response = trends.getItems().get(i);
            System.out.println("#" + (i+1) + " " + response.getName());
            System.out.println("Owner : " + response.getOwner());
            System.out.println("Language : " + response.getLanguage());
            System.out.println("Stars : " + response.getStargazersCount());
            System.out.println("Repository : " + response.getHtmlUrl());
            System.out.println("---------------------");
        }
        System.out.println("============================");
    }
}
