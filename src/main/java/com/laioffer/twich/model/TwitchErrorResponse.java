package com.laioffer.twich.model;

public record TwitchErrorResponse(
        String message,
        String error,
        String details
) {
}
