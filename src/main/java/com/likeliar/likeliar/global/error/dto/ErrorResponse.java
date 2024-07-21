package com.likeliar.likeliar.global.error.dto;

public record ErrorResponse(
        int statusCode,
        String message
) {
}