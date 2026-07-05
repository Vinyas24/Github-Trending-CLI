package com.githubtrends.service;

import java.util.ArrayList;
import java.util.List;

import com.githubtrends.model.Repository;

public class RepositoryFilter {
    public List<Repository> filterByMinimumStars(List<Repository> repositories,int minimumStars){
        List<Repository> filteredRepositories = new ArrayList<>();
        for(Repository repository: repositories){
            if(repository.getStargazersCount() >= minimumStars){
                filteredRepositories.add(repository);
            }
        }
        return filteredRepositories;
    }
}
