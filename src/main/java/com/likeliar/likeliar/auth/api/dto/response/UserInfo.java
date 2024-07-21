package com.likeliar.likeliar.auth.api.dto.response;

public record UserInfo(
        String email,
        String name,
        String picture
) {
}
