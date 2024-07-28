package com.likeliar.likeliar.gameRoom.exception;

import com.likeliar.likeliar.global.error.exception.NotFoundGroupException;

public class GameRoomMemberNotFoundException extends NotFoundGroupException {
    public GameRoomMemberNotFoundException(String message) {
        super(message);
    }

    public GameRoomMemberNotFoundException() {
        this("존재하지 않는 참가자 입니다.");
    }
}
