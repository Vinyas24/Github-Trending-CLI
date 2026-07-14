package com.githubtrends.ui;

import java.text.NumberFormat;
import java.util.List;

import com.githubtrends.model.Repository;

public class RepositoryPrinter {
    private static final String SEPARATOR = "================================================================";
    private static final int LABEL_WIDTH = 20;

    public static void printHeader() {
        System.out.println(SEPARATOR);
        System.out.printf("%42s%n",
                AnsiColors.BOLD + AnsiColors.BRIGHT_CYAN + "🚀GitHub Trending Repositories📈" + AnsiColors.RESET);
        System.out.println(SEPARATOR);
        System.out.println();
    }

    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();

    public static void printRepository(Repository repository, int rank) {
        String owner = repository.getOwner() == null ? "N/A" : repository.getOwner().getLogin();
        String language = repository.getLanguage() == null || repository.getLanguage().isBlank() ? "N/A"
                : repository.getLanguage();
        String description = repository.getDescription() == null || repository.getDescription().isBlank() ? "N/A"
                : repository.getDescription();
        String url = repository.getHtmlUrl() == null ? "N/A" : repository.getHtmlUrl();
        System.out.println(AnsiColors.BRIGHT_BLACK + "┌────────────────────────────────────────────────────────────────"
                + AnsiColors.RESET);
        System.out.printf("│ %s#%d %s%s%n", AnsiColors.BOLD + AnsiColors.BRIGHT_GREEN, rank + 1, repository.getName(),
                AnsiColors.RESET);
        System.out.println(AnsiColors.BRIGHT_BLACK + "├────────────────────────────────────────────────────────────────"
                + AnsiColors.RESET);
        System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%n", AnsiColors.BRIGHT_CYAN, "👤 Owner", AnsiColors.RESET,
                owner);
        System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%n", AnsiColors.BRIGHT_CYAN, "💻 Language",
                AnsiColors.RESET, language);
        System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%s%s%n", AnsiColors.BRIGHT_CYAN, "✴️ Stars",
                AnsiColors.RESET, AnsiColors.BRIGHT_YELLOW, NUMBER_FORMAT.format(repository.getStargazersCount()),
                AnsiColors.RESET);
        printWrappedLine("📝 Description", description);
        printWrappedLine("🔗 Repository", url);
        System.out.println(AnsiColors.BRIGHT_BLACK + "└────────────────────────────────────────────────────────────────"
                + AnsiColors.RESET);
        System.out.println();
    }

    private static void printWrappedLine(String label, String text) {
        final int MAX_WIDTH = 70;
        if (text == null || text.isBlank()) {
            text = "N/A";
        }
        boolean firstLine = true;
        while (text.length() > MAX_WIDTH) {
            int breakPoint = text.lastIndexOf(' ', MAX_WIDTH);
            if (breakPoint == -1) {
                breakPoint = MAX_WIDTH;
            }
            if (firstLine) {
                System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%n", AnsiColors.BRIGHT_CYAN, label,
                        AnsiColors.RESET, text.substring(0, breakPoint));
                firstLine = false;
            } else {
                System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s   %s%n", AnsiColors.BRIGHT_CYAN, "", AnsiColors.RESET,
                        text.substring(0, breakPoint));
            }
            text = text.substring(breakPoint).trim();
        }
        if (firstLine) {
            if ("🔗 Repository".equals(label)) {
                System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%s%s%s%n",AnsiColors.BRIGHT_CYAN,label,AnsiColors.RESET,AnsiColors.UNDERLINE,AnsiColors.BRIGHT_BLUE,text,AnsiColors.RESET);
            } else {
                System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%n",AnsiColors.BRIGHT_CYAN,label,AnsiColors.RESET,text);
            }
        } else {
            if ("🔗 Repository".equals(label)) {
                System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s   %s%s%s%s%n",AnsiColors.BRIGHT_CYAN,"",AnsiColors.RESET,AnsiColors.UNDERLINE,AnsiColors.BRIGHT_BLUE,text,AnsiColors.RESET);
            } else {
                System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s   %s%n",AnsiColors.BRIGHT_CYAN,"",AnsiColors.RESET,text);
            }
        }
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
