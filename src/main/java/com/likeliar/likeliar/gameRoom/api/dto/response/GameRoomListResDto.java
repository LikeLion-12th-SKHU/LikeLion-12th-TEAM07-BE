package com.likeliar.likeliar.gameRoom.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record GameRoomListResDto(
        List<GameRoomInfoResDto> gameRooms
) {
    public static GameRoomListResDto from(List<GameRoomInfoResDto> gameRooms){
        return GameRoomListResDto.builder()
                .gameRooms(gameRooms)
                .build();
    }
}
