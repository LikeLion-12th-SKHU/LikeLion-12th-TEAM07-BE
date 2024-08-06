package com.likeliar.likeliar.word.api.dto.response;

import com.likeliar.likeliar.word.domain.Word;
import lombok.Builder;

@Builder
public record WordInfoResDto(
        String subject, //주제
        String vocabulary, //단어
        String description //단어 뜻, 설명
) {
    public static WordInfoResDto from(Word word) {
        return WordInfoResDto.builder()
                .subject(word.getSubject())
                .vocabulary(word.getVocabulary())
                .description(word.getDescription())
                .build();

    }
}
