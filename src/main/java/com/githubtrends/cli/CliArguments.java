package com.githubtrends.cli;

import com.githubtrends.exceptions.InvalidCliArgumentException;

public class CliArguments {
    public static CliArguments parse(String[] args) {
        
        if (args.length < 2 || args.length % 2 == 1) {
            throw new InvalidCliArgumentException(
                    "Usage: java App <language> <repository-count> [--sort value] [--order value] [--page value] [--min-stars value]");
        }
        int count;
        try {
            count = Integer.parseInt(args[1]);
            if (count <= 0 || count > 100) {
                throw new InvalidCliArgumentException("Repository count must be between 1 and 100.\n" +
                        "GitHub Search API allows a maximum of 100 results per page.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCliArgumentException("Repository count must be a valid integer.");
        }

        SortType sort = SortType.STARS;
        OrderType order = OrderType.DESC;
        int page = 1;
        int minimumStars = 0;

        for (int i = 2; i < args.length; i += 2) {
            if ("--sort".equals(args[i])) {
                try {
                    sort = SortType.valueOf(args[i + 1].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new InvalidCliArgumentException(
                            "Invalid sort type: " + args[i + 1] + ".\nValid values: stars, forks, updated.");
                }
            }

            else if ("--order".equals(args[i])) {
                try {
                    order = OrderType.valueOf(args[i + 1].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new InvalidCliArgumentException(
                            "Invalid order type: " + args[i + 1] + ".\nValid values: asc, desc");
                }

            }

            else if ("--page".equals(args[i])) {
                try {
                    page = Integer.parseInt(args[i + 1]);
                    if (page <= 0) {
                        throw new InvalidCliArgumentException("Page number must be a positive value.");
                    }
                } catch (NumberFormatException e) {
                    throw new InvalidCliArgumentException("Page number must be a valid integer.");
                }

            } else if ("--min-stars".equals(args[i])) {
                try {
                    minimumStars = Integer.parseInt(args[i + 1]);
                    if (minimumStars < 0) {
                        throw new InvalidCliArgumentException("Minimum stars must be 0 or greater.");
                    }
                } catch (NumberFormatException e) {
                    throw new InvalidCliArgumentException("Minimum stars must be a valid integer.");
                }
            } else {
                throw new InvalidCliArgumentException(
                        "Found unknown flag: " + args[i] + ".\nValid flags: --sort, --order, --page, --min-stars");
            }
        }

        return new CliArguments(args[0], count, sort, order, page, minimumStars);
    }

    private final String language;
    private final int count;
    private final SortType sort;
    private final OrderType order;
    private final int page;
    private final int minimumStars;

    public int getPage() {
        return page;
    }

    public String getLanguage() {
        return language;
    }

    public int getCount() {
        return count;
    }

    public SortType getSort() {
        return sort;
    }

    public OrderType getOrder() {
        return order;
    }

    private CliArguments(String language, int count, SortType sort, OrderType order, int page, int minimumStars) {
        this.language = language;
        this.count = count;
        this.sort = sort;
        this.order = order;
        this.page = page;
        this.minimumStars = minimumStars;
    }

    public int getMinimumStars() {
        return minimumStars;
    }
}
