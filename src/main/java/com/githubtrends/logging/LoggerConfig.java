package com.githubtrends.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {
    private static final Logger LOGGER = Logger.getLogger("GitHubTrendingCLI");

    static {
        try {
            Files.createDirectories(Paths.get("logs/"));
            FileHandler fileHandler = new FileHandler("logs/github-trending-cli.log");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    private LoggerConfig() {}

    public static Logger getLogger() {
        return LOGGER;
    }
}
