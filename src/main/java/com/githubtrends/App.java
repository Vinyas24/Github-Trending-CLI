package com.githubtrends;

import java.io.IOException;
import com.githubtrends.client.GithubClient;

public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        GithubClient gitTrend = new GithubClient();
        String trends = gitTrend.findTrendingRepositories();
        System.out.println(trends);
    }
}
