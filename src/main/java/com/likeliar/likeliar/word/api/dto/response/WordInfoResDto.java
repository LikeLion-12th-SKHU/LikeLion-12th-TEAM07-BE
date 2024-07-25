package com.likeliar.likeliar.word.api.dto.response;

import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomInfoResDto;
import com.likeliar.likeliar.gameRoom.domain.GameRoom;
import com.likeliar.likeliar.word.domain.Word;
import lombok.Builder;

@Builder
public record WordInfoResDto (
        String topic, //주제
        String vocabulary, //단어
        String explanation //단어 뜻, 설명
) {
    public static WordInfoResDto from(Word word){
        return WordInfoResDto.builder()
                .topic(word.getTopic())
                .vocabulary(word.getVocabulary())
                .explanation(word.getExplanation())
                .build();

    }
}
