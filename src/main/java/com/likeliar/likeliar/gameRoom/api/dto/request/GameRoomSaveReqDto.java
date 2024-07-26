package com.likeliar.likeliar.gameRoom.api.dto.request;

public record GameRoomSaveReqDto(
        String roomName,

        Long playerNumber,

        String subject,

        String content
) {

}
