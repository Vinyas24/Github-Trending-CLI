package com.githubtrends.cli;

public class CliArguments {
    public static CliArguments parse(String[] args) throws IllegalArgumentException {
        if (args.length <= 1) {
            throw new IllegalArgumentException("Usage: java App <language> <repository-count>");
        }
        int count = 0;
        count = Integer.parseInt(args[1]);
        if (count <= 0) {
            throw new IllegalArgumentException("Repository count must be greater than 0.");
        }

        return new CliArguments(args[0], count);
    }

    private final String language;
    private final int count;

    public String getLanguage() {
        return language;
    }

    public int getCount() {
        return count;
    }

    private CliArguments(String language, int count) {
        this.language = language;
        this.count = count;
    }
}
