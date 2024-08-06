package com.likeliar.likeliar.word.api.dto.request;

public record WordSaveReqDto(
        String subject,
        String vocabulary,
        String description
) {
}
