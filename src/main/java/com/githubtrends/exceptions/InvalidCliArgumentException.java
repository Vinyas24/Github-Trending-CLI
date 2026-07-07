package com.githubtrends.exceptions;

public class InvalidCliArgumentException extends IllegalArgumentException {
    public InvalidCliArgumentException(String message) {
        super(message);
    }
}
