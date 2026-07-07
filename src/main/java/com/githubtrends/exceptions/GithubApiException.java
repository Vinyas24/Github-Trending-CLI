package com.githubtrends.exceptions;

import java.io.IOException;

public class GithubApiException extends IOException {
    public GithubApiException(String message) {
        super(message);
    }
}
