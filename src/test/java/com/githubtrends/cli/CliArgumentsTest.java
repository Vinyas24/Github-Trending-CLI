package com.githubtrends.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.githubtrends.exceptions.InvalidCliArgumentException;

public class CliArgumentsTest {
    @Test
    void parseDefaultValues() {
        String[] args = { "java", "10" };
        CliArguments cliArguments = CliArguments.parse(args);
        assertEquals("java", cliArguments.getLanguage());
        assertEquals(10, cliArguments.getCount());
        assertEquals(SortType.STARS, cliArguments.getSort());
        assertEquals(OrderType.DESC, cliArguments.getOrder());
        assertEquals(1, cliArguments.getPage());
        assertEquals(0, cliArguments.getMinimumStars());
    }

    @Test
    void parseAllOptionalValues() {
        String[] args = { "java", "10", "--sort", "forks", "--order", "asc", "--page", "2", "--min-stars", "1000" };
        CliArguments cliArguments = CliArguments.parse(args);
        assertEquals("java", cliArguments.getLanguage());
        assertEquals(10, cliArguments.getCount());
        assertEquals(SortType.FORKS, cliArguments.getSort());
        assertEquals(OrderType.ASC, cliArguments.getOrder());
        assertEquals(2, cliArguments.getPage());
        assertEquals(1000, cliArguments.getMinimumStars());
    }

    @Test
    void throwExceptionWhenCountGreaterThan100() {
        String[] args = { "java", "1000" };
        InvalidCliArgumentException e = assertThrows(InvalidCliArgumentException.class, () -> CliArguments.parse(args));
        assertEquals(
                "Repository count must be between 1 and 100.\nGitHub Search API allows a maximum of 100 results per page.",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenCountIsZero() {
        String[] args = { "java", "0" };
        InvalidCliArgumentException e = assertThrows(InvalidCliArgumentException.class, () -> CliArguments.parse(args));
        assertEquals(
                "Repository count must be between 1 and 100.\nGitHub Search API allows a maximum of 100 results per page.",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenCountIsNegative() {
        String[] args = { "java", "-10" };
        InvalidCliArgumentException e = assertThrows(InvalidCliArgumentException.class, () -> CliArguments.parse(args));
        assertEquals(
                "Repository count must be between 1 and 100.\nGitHub Search API allows a maximum of 100 results per page.",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenCountIsNotANumber() {
        String[] args = { "java", "abc" };
        InvalidCliArgumentException e = assertThrows(InvalidCliArgumentException.class, () -> CliArguments.parse(args));
        assertEquals(
                "Repository count must be a valid integer.",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenSortIsInvalid() {
        String[] args = { "java", "10", "--sort", "random" };
        InvalidCliArgumentException e = assertThrows(
                InvalidCliArgumentException.class,
                () -> CliArguments.parse(args));
        assertEquals(
                "Invalid sort type: random.\nValid values: stars, forks, updated.",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenOrderIsInvalid() {
        String[] args = { "java", "10", "--order", "wrong" };
        InvalidCliArgumentException e = assertThrows(
                InvalidCliArgumentException.class,
                () -> CliArguments.parse(args));
        assertEquals(
                "Invalid order type: wrong.\nValid values: asc, desc",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenPageIsZero() {
        String[] args = { "java", "10", "--page", "0" };
        InvalidCliArgumentException e = assertThrows(
                InvalidCliArgumentException.class,
                () -> CliArguments.parse(args));
        assertEquals(
                "Page number must be a positive value.",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenPageIsNegative() {
        String[] args = { "java", "10", "--page", "-5" };
        InvalidCliArgumentException e = assertThrows(
                InvalidCliArgumentException.class,
                () -> CliArguments.parse(args));
        assertEquals(
                "Page number must be a positive value.",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenPageIsNotANumber() {
        String[] args = { "java", "10", "--page", "abc" };
        InvalidCliArgumentException e = assertThrows(
                InvalidCliArgumentException.class,
                () -> CliArguments.parse(args));
        assertEquals(
                "Page number must be a valid integer.",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenMinimumStarsIsNegative() {
        String[] args = { "java", "10", "--min-stars", "-100" };
        InvalidCliArgumentException e = assertThrows(
                InvalidCliArgumentException.class,
                () -> CliArguments.parse(args));
        assertEquals(
                "Minimum stars must be 0 or greater.",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenMinimumStarsIsNotANumber() {
        String[] args = { "java", "10", "--min-stars", "abc" };
        InvalidCliArgumentException e = assertThrows(
                InvalidCliArgumentException.class,
                () -> CliArguments.parse(args));
        assertEquals(
                "Minimum stars must be a valid integer.",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenUnknownFlagIsProvided() {
        String[] args = { "java", "10", "--hello", "world" };
        InvalidCliArgumentException e = assertThrows(
                InvalidCliArgumentException.class,
                () -> CliArguments.parse(args));
        assertEquals(
                "Found unknown flag: --hello.\nValid flags: --sort, --order, --page, --min-stars",
                e.getMessage());
    }

    @Test
    void throwExceptionWhenArgumentsAreMissing() {
        String[] args = { "java" };
        InvalidCliArgumentException e = assertThrows(
                InvalidCliArgumentException.class,
                () -> CliArguments.parse(args));
        assertEquals(
                "Usage: java App <language> <repository-count> [--sort value] [--order value] [--page value] [--min-stars value]",
                e.getMessage());
    }

    @Test
    void parseSomeOptionalValues() {
        String[] args = {
                "java",
                "20",
                "--page", "3",
                "--min-stars", "500"
        };
        CliArguments cliArguments = CliArguments.parse(args);
        assertEquals("java", cliArguments.getLanguage());
        assertEquals(20, cliArguments.getCount());
        assertEquals(SortType.STARS, cliArguments.getSort());
        assertEquals(OrderType.DESC, cliArguments.getOrder());
        assertEquals(3, cliArguments.getPage());
        assertEquals(500, cliArguments.getMinimumStars());
    }
}
