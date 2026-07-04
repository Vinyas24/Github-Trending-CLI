package com.githubtrends;

import java.io.IOException;

import com.githubtrends.cli.CliArguments;
import com.githubtrends.client.GithubClient;
import com.githubtrends.model.SearchResponse;
import com.githubtrends.ui.RepositoryPrinter;

public class App {
    public static void main(String[] args) {
        try {
            GithubClient githubClient = new GithubClient();

            CliArguments cliArgument = CliArguments.parse(args);

            SearchResponse trends = githubClient.findTrendingRepositories(cliArgument.getLanguage(),
                    cliArgument.getCount(),cliArgument.getSort(), cliArgument.getOrder());

            RepositoryPrinter.printRepositories(trends.getItems());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Operation interrupted.");
        }   
    }
}
