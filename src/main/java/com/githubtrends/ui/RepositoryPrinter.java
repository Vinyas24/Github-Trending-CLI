package com.githubtrends.ui;

import java.text.NumberFormat;
import java.util.List;

import com.githubtrends.model.Repository;

public class RepositoryPrinter {
    private static final String SEPARATOR = "====================================";
    private static final int LABEL_WIDTH = 20;
    public static void printHeader() {
        System.out.println(SEPARATOR);
        System.out.println("    GitHub Trending Repositories");
        System.out.println(SEPARATOR);
    }

    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();
    public static void printRepository(Repository repository, int rank) {
        System.out.println("#" + (rank + 1) + " " + repository.getName());
        System.out.printf("%-"+LABEL_WIDTH+"s : %s%n", "👤 Owner", repository.getOwner());
        System.out.printf("%-"+LABEL_WIDTH+"s : %s%n", "💻 Language",
                (repository.getLanguage()) == null ? "N/A" : repository.getLanguage());
        System.out.printf("%-"+LABEL_WIDTH+"s : %s%n", "⭐ Stars", NUMBER_FORMAT.format(repository.getStargazersCount()));
        System.out.printf("%-"+LABEL_WIDTH+"s : %s%n", "📎 Repository", repository.getHtmlUrl());
        System.out.println("-------------------------------------------------------");
    }

    public static void printFooter(int total) {
        System.out.println(SEPARATOR);
        System.out.println("Total Repositories : " + total);
        System.out.println(SEPARATOR);
    }

    public static void printRepositories(List<Repository> repositories) {
        printHeader();

        for (int i = 0; i < repositories.size(); i++) {
            printRepository(repositories.get(i), i);
        }

        printFooter(repositories.size());
    }

    private RepositoryPrinter() {
    }

}
