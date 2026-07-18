package com.githubtrends;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.githubtrends.cli.CliArguments;
import com.githubtrends.client.GithubClient;
import com.githubtrends.logging.LoggerConfig;
import com.githubtrends.model.Repository;
import com.githubtrends.model.SearchResponse;
import com.githubtrends.service.RepositoryFilter;
import com.githubtrends.ui.HelpPrinter;
import com.githubtrends.ui.RepositoryPrinter;

public class App {
    private static final Logger LOGGER = LoggerConfig.getLogger();

    public static void main(String[] args) {
        LOGGER.info("GitHub Trending CLI started.");

        if (args.length == 1 && args[0].equals("--help")) {
            HelpPrinter.printHelp();
            return;
        }

        try {
            GithubClient githubClient = new GithubClient();
            CliArguments cliArguments = CliArguments.parse(args);
            LOGGER.info("Language=" + cliArguments.getLanguage()
                    + ", Count=" + cliArguments.getCount()
                    + ", Sort=" + cliArguments.getSort()
                    + ", Order=" + cliArguments.getOrder()
                    + ", Duration=" + cliArguments.getDuration()
                    + ", Page=" + cliArguments.getPage()
                    + ", MinStars=" + cliArguments.getMinimumStars());

            LOGGER.info("Fetching repositories from GitHub.");
            SearchResponse searchResponse = githubClient.findTrendingRepositories(
                    cliArguments.getLanguage(),
                    cliArguments.getCount(),
                    cliArguments.getSort(),
                    cliArguments.getOrder(),
                    cliArguments.getDuration(),
                    cliArguments.getPage());

            RepositoryFilter repositoryFilter = new RepositoryFilter();

            LOGGER.info("Fetched " + searchResponse.getItems().size() + " repositories from GitHub.");

            List<Repository> filteredRepositories = repositoryFilter.filterByMinimumStars(searchResponse.getItems(),
                    cliArguments.getMinimumStars());
            LOGGER.info("Filtered down to " + filteredRepositories.size() + " repositories.");

            if (filteredRepositories.isEmpty()) {
                System.out.println("No repositories found.");
                return;
            }
            
            RepositoryPrinter.printRepositories(filteredRepositories);
            LOGGER.info("Application finished successfully.");
        }

        catch (IllegalArgumentException e) {
            LOGGER.warning(e.getMessage());
            System.out.println(e.getMessage());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            LOGGER.severe("Application interrupted while waiting for GitHub response.");
            Thread.currentThread().interrupt();
            System.out.println("Operation interrupted.");
        }
    }
}
