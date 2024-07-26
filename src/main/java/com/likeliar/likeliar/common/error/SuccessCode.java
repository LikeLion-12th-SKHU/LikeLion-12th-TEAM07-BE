package com.likeliar.likeliar.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /**
     * 200 OK
     */

    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    GAMEROOM_JOIN_SUCCESS(HttpStatus.OK, "게임방에 성공적으로 참여했습니다."),
    GAMEROOM_UPDATE_SUCCESS(HttpStatus.OK, "게임방이 성공적으로 수정되었습니다."),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "사용자가 성공적으로 수정되었습니다."),
    GAMEROOM_DELETE_SUCCESS(HttpStatus.OK, "게임방이 성공적으로 삭제되었습니다."),
    MEMBER_DELETE_SUCCESS(HttpStatus.OK, "사용자가 성공적으로 삭제되었습니다."),

    /**
     * 201 CREATED
     */

    GAMEROOM_SAVE_SUCCESS(HttpStatus.CREATED, "게임방이 성공적으로 등록되었습니다."),
    MEMBER_SAVE_SUCCESS(HttpStatus.CREATED, "사용자가 성공적으로 등록되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
