package com.githubtrends.ui;

public class HelpPrinter {

    public static void printHelp() {
        System.out.println("""
=========================================
          GitHub Trending CLI
=========================================

Fetch trending GitHub repositories by language.

USAGE
  java App <language> <count> [options]

OPTIONS
  --sort <value>        stars | forks | updated
  --order <value>       asc | desc
  --page <number>       Page number (default: 1)
  --min-stars <number>  Minimum stars (default: 0)
  --help                Show this help message
""");
    }
}