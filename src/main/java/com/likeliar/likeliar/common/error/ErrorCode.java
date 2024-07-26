package com.likeliar.likeliar.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /**
     * 404 NOT FOUND
     */
    GAMEROOMS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 게임방이 없습니다. gameRoomId = ", "NOT_FOUND_404"),
    MEMBERS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 사용자가 없습니다. memberId = ", "NOT_FOUND_404"),
    GAMEROOMS_MEMBER_MAPPING_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 게임 방의 참여자가 없습니다. memberId = ",
            "NOT_FOUND_404"),
    WORDS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 단어가 없습니다. wordId = ", "NOT_FOUND_404"),
  
    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다", "INTERNAL_SERVER_ERROR_500"),

    /**
     * 400 BAD REQUEST
     */
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "유효성 검사에 맞지 않습니다.", "BAD_REQUEST_400");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}

