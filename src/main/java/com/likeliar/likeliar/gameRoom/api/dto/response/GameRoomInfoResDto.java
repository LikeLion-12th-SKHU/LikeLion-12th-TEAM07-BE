package com.likeliar.likeliar.gameRoom.api.dto.response;

import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomSaveReqDto;
import com.likeliar.likeliar.gameRoom.domain.GameRoom;
import lombok.Builder;

@Builder
public record GameRoomInfoResDto(
        String roomName,
        long playerNumber,
        long time,
        String subject,
        String content
) {

    public static GameRoomInfoResDto from(GameRoom gameRoom){
        return GameRoomInfoResDto.builder()
                .roomName(gameRoom.getRoomName())
                .playerNumber(gameRoom.getPlayerNumber())
                .time(gameRoom.getTime())
                .subject(gameRoom.getSubject())
                .content(gameRoom.getContent())
                .build();

    }
}
