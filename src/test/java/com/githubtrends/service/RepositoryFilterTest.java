package com.githubtrends.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.githubtrends.model.Repository;

public class RepositoryFilterTest {
    private Repository createRepository(long stars) {
        Repository repository = new Repository();
        repository.setStargazersCount(stars);
        return repository;
    }

    @Test
    void returnAllRepositoriesWhenMinimumStarsIsZero() {
        List<Repository> repositories = List.of(
                createRepository(10),
                createRepository(100),
                createRepository(1000));
        RepositoryFilter filter = new RepositoryFilter();
        List<Repository> filtered = filter.filterByMinimumStars(repositories, 0);
        assertEquals(3, filtered.size());
    }

    @Test
    void filterRepositoriesBelowMinimumStars() {
        List<Repository> repositories = List.of(
                createRepository(10),
                createRepository(500),
                createRepository(1000));
        RepositoryFilter filter = new RepositoryFilter();
        List<Repository> filtered = filter.filterByMinimumStars(repositories, 500);
        assertEquals(2, filtered.size());
        assertEquals(500, filtered.get(0).getStargazersCount());
        assertEquals(1000, filtered.get(1).getStargazersCount());
    }

    @Test
    void returnEmptyListWhenNoRepositoryMatches() {
        List<Repository> repositories = List.of(
                createRepository(10),
                createRepository(20));
        RepositoryFilter filter = new RepositoryFilter();
        List<Repository> filtered = filter.filterByMinimumStars(repositories, 100);
        assertEquals(0, filtered.size());
    }

    @Test
    void returnEmptyListWhenInputListIsEmpty() {
        RepositoryFilter filter = new RepositoryFilter();
        List<Repository> filtered = filter.filterByMinimumStars(List.of(), 100);
        assertEquals(0, filtered.size());
    }

    @Test
    void includeRepositoryWhenStarsEqualMinimumStars() {
        List<Repository> repositories = List.of(
                createRepository(100));
        RepositoryFilter filter = new RepositoryFilter();
        List<Repository> filtered = filter.filterByMinimumStars(repositories, 100);
        assertEquals(1, filtered.size());
        assertEquals(100, filtered.get(0).getStargazersCount());
    }

    @Test
    void returnAllRepositoriesWhenAllMeetMinimumStars() {
        List<Repository> repositories = List.of(
                createRepository(500),
                createRepository(600),
                createRepository(700));
        RepositoryFilter filter = new RepositoryFilter();
        List<Repository> filtered = filter.filterByMinimumStars(repositories, 500);
        assertEquals(3, filtered.size());
    }
}
