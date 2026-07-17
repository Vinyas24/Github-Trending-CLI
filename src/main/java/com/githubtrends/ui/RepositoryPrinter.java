package com.githubtrends.ui;

import java.text.NumberFormat;
import java.util.List;

import com.githubtrends.model.Repository;

public class RepositoryPrinter {
    private static final String SEPARATOR = "═".repeat(70);
    private static final int LABEL_WIDTH = 20;
    private static final int BOX_WIDTH = 66;
    private static final int CONTENT_WIDTH = BOX_WIDTH - LABEL_WIDTH - 4;
    private static final String TOP_BORDER = "┌" + "─".repeat(BOX_WIDTH) + "┐";
    private static final String MIDDLE_BORDER = "├" + "─".repeat(BOX_WIDTH) + "┤";
    private static final String BOTTOM_BORDER = "└" + "─".repeat(BOX_WIDTH) + "┘";

    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();

    public static void printRepository(Repository repository, int rank) {
        String owner = repository.getOwner() == null ? "N/A" : repository.getOwner().getLogin();
        String language = repository.getLanguage() == null || repository.getLanguage().isBlank() ? "N/A"
                : repository.getLanguage();
        String description = repository.getDescription() == null || repository.getDescription().isBlank() ? "N/A"
                : repository.getDescription();
        String url = repository.getHtmlUrl() == null ? "N/A" : repository.getHtmlUrl();

        System.out.println(AnsiColors.BRIGHT_BLACK + TOP_BORDER + AnsiColors.RESET);
        printTitle("[#" + (rank + 1) + "] " + repository.getName());

        System.out.println(AnsiColors.BRIGHT_BLACK + MIDDLE_BORDER + AnsiColors.RESET);
        System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%s%s│%n", AnsiColors.BRIGHT_CYAN,
                "◉ Owner", AnsiColors.RESET, AnsiColors.BOLD, padRight(owner, CONTENT_WIDTH), AnsiColors.RESET);
        System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%s%s│%n", AnsiColors.BRIGHT_PURPLE,
                "⌨ Language", AnsiColors.RESET, AnsiColors.BOLD, padRight(language, CONTENT_WIDTH), AnsiColors.RESET);
        System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%s%s%s│%n", AnsiColors.BRIGHT_YELLOW,
                "★ Stars", AnsiColors.RESET, AnsiColors.BRIGHT_YELLOW, AnsiColors.BOLD,
                padRight(NUMBER_FORMAT.format(repository.getStargazersCount()), CONTENT_WIDTH), AnsiColors.RESET);
        printWrappedLine("✎ Description", description, false);
        printWrappedLine("↗ Repository", url, true);

        System.out.println(AnsiColors.BRIGHT_BLACK + BOTTOM_BORDER + AnsiColors.RESET);
    }

    private static void printWrappedLine(String label, String text, boolean isUrl) {
        final int MAX_WIDTH = CONTENT_WIDTH;
        if (text == null || text.isBlank()) {
            text = "N/A";
        }
        boolean firstLine = true;

        while (text.length() > MAX_WIDTH) {
            int breakPoint = text.lastIndexOf(' ', MAX_WIDTH);
            if (breakPoint == -1) {
                breakPoint = MAX_WIDTH;
            }

            String part = text.substring(0, breakPoint);

            if (firstLine) {
                if (isUrl) {
                    System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%s%s%s%s│%n", AnsiColors.BRIGHT_BLUE, label,
                            AnsiColors.RESET, AnsiColors.UNDERLINE, AnsiColors.BRIGHT_BLUE, AnsiColors.BOLD,
                            padRight(part, CONTENT_WIDTH), AnsiColors.RESET);
                } else {
                    System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%s%s│%n", AnsiColors.BRIGHT_WHITE, label,
                            AnsiColors.RESET, AnsiColors.BOLD, padRight(part, CONTENT_WIDTH), AnsiColors.RESET);
                }
                firstLine = false;
            } else {
                if (isUrl) {
                    System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s   %s%s%s%s%s│%n", AnsiColors.BRIGHT_BLUE, "",
                            AnsiColors.RESET, AnsiColors.UNDERLINE, AnsiColors.BRIGHT_BLUE, AnsiColors.BOLD,
                            padRight(part, CONTENT_WIDTH), AnsiColors.RESET);
                } else {
                    System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s   %s%s%s│%n", AnsiColors.BRIGHT_WHITE, "",
                            AnsiColors.RESET, AnsiColors.BOLD, padRight(part, CONTENT_WIDTH), AnsiColors.RESET);
                }
            }

            text = text.substring(breakPoint).trim();
        }

        if (firstLine) {
            if (isUrl) {
                System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%s%s%s%s│%n", AnsiColors.BRIGHT_BLUE, label,
                        AnsiColors.RESET, AnsiColors.UNDERLINE, AnsiColors.BRIGHT_BLUE, AnsiColors.BOLD, padRight(text, CONTENT_WIDTH),
                        AnsiColors.RESET);
            } else {
                System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s : %s%s%s│%n", AnsiColors.BRIGHT_WHITE, label,
                        AnsiColors.RESET, AnsiColors.BOLD, padRight(text, CONTENT_WIDTH), AnsiColors.RESET);
            }
        } else {
            if (isUrl) {
                System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s   %s%s%s%s%s│%n", AnsiColors.BRIGHT_BLUE, "",
                        AnsiColors.RESET, AnsiColors.UNDERLINE, AnsiColors.BRIGHT_BLUE, AnsiColors.BOLD, padRight(text, CONTENT_WIDTH),
                        AnsiColors.RESET);
            } else {
                System.out.printf("│ %s%-" + LABEL_WIDTH + "s%s   %s%s%s│%n", AnsiColors.BRIGHT_WHITE, "", AnsiColors.RESET,
                        AnsiColors.BOLD, padRight(text, CONTENT_WIDTH), AnsiColors.RESET);
            }
        }
    }

    private static void printTitle(String title) {
        System.out.print("│ ");
        System.out.print(AnsiColors.BOLD + AnsiColors.BRIGHT_GREEN + title + AnsiColors.RESET);
        System.out.print(" ".repeat(Math.max(0, BOX_WIDTH - title.length() - 1)));
        System.out.println("│");
    }

    private static void printCentered(String text) {
        int width = SEPARATOR.length();
        int padding = Math.max(0, (width - text.length()) / 2);
        System.out.printf("%" + (padding + text.length()) + "s%n", text);
    }

    public static void printHeader() {
        System.out.println(SEPARATOR);
        printCentered(
                AnsiColors.BOLD + AnsiColors.BRIGHT_CYAN + "⌬ GitHub Trending Repositories |⎇" + AnsiColors.RESET);
        System.out.println(SEPARATOR);
        System.out.println();
    }

    public static void printFooter(int total) {
        System.out.println(SEPARATOR);
        printCentered(AnsiColors.BOLD + AnsiColors.BRIGHT_GREEN + "✔ Total Repositories : " + total + AnsiColors.RESET);
        System.out.println(SEPARATOR);
    }

    private static String padRight(String text, int width) {
        return String.format("%-" + width + "s", text);
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
