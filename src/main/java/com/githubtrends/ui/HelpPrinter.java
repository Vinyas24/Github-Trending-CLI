package com.githubtrends.ui;

public class HelpPrinter {

  private static final String SEPARATOR = "═".repeat(70);

  private static void printCentered(String text, String color) {
    int width = SEPARATOR.length();
    int padding = Math.max(0, (width - text.length()) / 2);

    System.out.print(" ".repeat(padding));
    System.out.println(color + text + AnsiColors.RESET);
  }

  public static void printHelp() {

    System.out.println(AnsiColors.BRIGHT_BLACK + SEPARATOR + AnsiColors.RESET);

    printCentered("⌬ GitHub Trending CLI Help |⎇", AnsiColors.BOLD + AnsiColors.BRIGHT_CYAN);

    System.out.println(AnsiColors.BRIGHT_BLACK + SEPARATOR + AnsiColors.RESET);
    System.out.println();

    System.out.println(AnsiColors.BRIGHT_GREEN + " DESCRIPTION" + AnsiColors.RESET);
    System.out.println("    Fetch trending GitHub repositories using the GitHub Search API.");
    System.out.println();

    System.out.println(AnsiColors.BRIGHT_GREEN + " USAGE" + AnsiColors.RESET);
    System.out.println("    "
        + AnsiColors.BRIGHT_YELLOW + "java App"
        + AnsiColors.RESET + " "
        + AnsiColors.BRIGHT_CYAN + "<language>"
        + AnsiColors.RESET + " "
        + AnsiColors.BRIGHT_CYAN + "<count>"
        + AnsiColors.RESET + " "
        + AnsiColors.BRIGHT_PURPLE + "[options]"
        + AnsiColors.RESET);
    System.out.println();

    System.out.println(AnsiColors.BRIGHT_GREEN + " OPTIONS" + AnsiColors.RESET);

    printOption("   --sort <value>", "stars | forks | updated");
    printOption("   --order <value>", "asc | desc");
    printOption("   --duration <value>", "day | week | month | year (default: week)");
    printOption("   --page <number>", "Page number (default: 1)");
    printOption("   --min-stars <n>", "Minimum stars (default: 0)");
    printOption("   --help", "Show this help message");

    System.out.println();

    System.out.println(AnsiColors.BRIGHT_GREEN + " EXAMPLES" + AnsiColors.RESET);

    printExample("java App java 10");
    printExample("java App python 20 --sort forks");
    printExample("java App javascript 15 --order asc");
    printExample("java App java 25 --duration month");
    printExample("java App go 10 --min-stars 50000");

    System.out.println();
    System.out.println(AnsiColors.BRIGHT_BLACK + SEPARATOR + AnsiColors.RESET);
  }

  private static void printOption(String option, String description) {
    System.out.printf("%s%-22s%s %s%n", AnsiColors.BRIGHT_CYAN, option, AnsiColors.RESET, description);
  }

  private static void printExample(String example) {
    System.out.println("    " + AnsiColors.BRIGHT_YELLOW + example + AnsiColors.RESET);
  }

  private HelpPrinter() {
  }
}