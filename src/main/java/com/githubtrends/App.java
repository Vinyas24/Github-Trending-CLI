package com.githubtrends;

import java.io.IOException;

import com.githubtrends.cli.CliArguments;
import com.githubtrends.client.GithubClient;
import com.githubtrends.model.SearchResponse;
import com.githubtrends.service.RepositoryFilter;
import com.githubtrends.ui.HelpPrinter;
import com.githubtrends.ui.RepositoryPrinter;

public class App {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--help")) {
            HelpPrinter.printHelp();
            return;
        }
        try {
            GithubClient githubClient = new GithubClient();

            CliArguments cliArgument = CliArguments.parse(args);

            SearchResponse searchResponse = githubClient.findTrendingRepositories(cliArgument.getLanguage(),
                    cliArgument.getCount(), cliArgument.getSort(), cliArgument.getOrder(), cliArgument.getPage());
            RepositoryFilter repositoryFilter = new RepositoryFilter();
            RepositoryPrinter.printRepositories(
                    repositoryFilter.filterByMinimumStars(searchResponse.getItems(), cliArgument.getMinimumStars()));

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
