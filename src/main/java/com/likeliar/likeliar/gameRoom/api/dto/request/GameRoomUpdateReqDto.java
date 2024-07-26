package com.likeliar.likeliar.gameRoom.api.dto.request;

public record GameRoomUpdateReqDto(
        String roomName,

        Long playerNumber,

        String subject,

        String content
) {
}
