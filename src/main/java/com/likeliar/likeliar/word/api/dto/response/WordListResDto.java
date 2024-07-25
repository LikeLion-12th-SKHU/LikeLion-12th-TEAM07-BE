package com.likeliar.likeliar.word.api.dto.response;

import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomInfoResDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomListResDto;
import lombok.Builder;

import java.util.List;

@Builder
public record WordListResDto(
        List<WordInfoResDto> words

) {
    public static WordListResDto from(List<WordInfoResDto> words){
        return WordListResDto.builder()
                .words(words)
                .build();
    }
}
