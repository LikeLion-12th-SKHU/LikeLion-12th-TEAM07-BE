package com.likeliar.likeliar.gameRoom.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record GameRoomUpdateReqDto(
        @NotBlank(message = "방 이름을 필수로 입력해야 합니다.")
        String roomName,
        @NotBlank(message = "방 이름을 필수로 입력해야 합니다.")
        long playerNumber,
        @NotBlank(message = "방 이름을 필수로 입력해야 합니다.")
        long time,
        @NotBlank(message = "방 이름을 필수로 입력해야 합니다.")
        String subject,
        @NotBlank(message = "방 이름을 필수로 입력해야 합니다.")
        String content
) {
}