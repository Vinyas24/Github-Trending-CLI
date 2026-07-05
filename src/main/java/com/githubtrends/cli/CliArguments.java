package com.githubtrends.cli;

public class CliArguments {
    public static CliArguments parse(String[] args) throws IllegalArgumentException {
        if (args.length <= 1 || args.length > 5) {
            throw new IllegalArgumentException("Usage: java App <language> <repository-count> [sort] [order] [page]");
        }

        int count = Integer.parseInt(args[1]);
        if (count <= 0) {
            throw new IllegalArgumentException("Repository count must be greater than 0.");
        }

        SortType sort = SortType.STARS;
        if (args.length == 3) {
            try {
                sort = SortType.valueOf(args[2].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Invalid sort type: " + args[2] + ".\nValid values: stars, forks, updated.");
            }
        }

        OrderType order = OrderType.DESC;
        if (args.length == 4) {
            try {
                order = OrderType.valueOf(args[3].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid order type: " + args[3] + ".\nValid values: asc, desc");
            }
        }
        int page = 2;
        if (args.length == 5) {
            try {
                page = Integer.parseInt(args[4]);
                if (page <= 0) {
                    throw new IllegalArgumentException("Page number must be positive value.");
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Page number must be valid integer.");
            }

        }

        return new CliArguments(args[0], count, sort, order, page);
    }

    private final String language;
    private final int count;
    private final SortType sort;
    private final OrderType order;
    private final int page;

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

    private CliArguments(String language, int count, SortType sort, OrderType order, int page) {
        this.language = language;
        this.count = count;
        this.sort = sort;
        this.order = order;
        this.page = page;
    }
}
