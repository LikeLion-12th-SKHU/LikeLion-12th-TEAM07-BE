package com.likeliar.likeliar.gameRoom.exception;

import com.likeliar.likeliar.global.error.exception.NotFoundGroupException;

public class GameRoomNotFoundException extends NotFoundGroupException {
    public GameRoomNotFoundException(String message) {
        super(message);
    }

    public GameRoomNotFoundException() {
        this("존재하지 않는 게임 입니다.");
    }
}
