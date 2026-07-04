package com.githubtrends.cli;

public class CliArguments {
    public static CliArguments parse(String[] args) throws IllegalArgumentException {
        if (args.length <= 1 || args.length > 4) {
            throw new IllegalArgumentException("Usage: java App <language> <repository-count> [sort] [option]");
        }
          
        int count = Integer.parseInt(args[1]);
        if (count <= 0) {
            throw new IllegalArgumentException("Repository count must be greater than 0.");
        }
        
        SortType sort = SortType.STARS;
        if(args.length == 3){
            try {
                sort = SortType.valueOf(args[2].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid sort type: "+args[2]+".\nValid values: stars, forks, updated.");
            }
        }
        OrderType order = OrderType.DESC;
        if(args.length == 4){
                try {
                    order = OrderType.valueOf(args[3].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid order type: " + args[3] + ".\nValid values: asc, desc");
                }
        }

        return new CliArguments(args[0], count, sort, order);
    }

    private final String language;
    private final int count;
    private final SortType sort;
    private final OrderType order;

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

    private CliArguments(String language, int count, SortType sort, OrderType order) {
        this.language = language;
        this.count = count;
        this.sort = sort;
        this.order = order;
    }
}
