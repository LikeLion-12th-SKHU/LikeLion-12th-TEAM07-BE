package com.likeliar.likeliar.word.api.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record WordListResDto(
        String subject,
        List<WordInfoResDto> words

) {
    public static WordListResDto from(String subject, List<WordInfoResDto> words) {
        return WordListResDto.builder()
                .subject(subject)
                .words(words)
                .build();
    }
}
