package com.likeliar.likeliar.common.dto;

import com.likeliar.likeliar.common.error.ErrorCode;
import com.likeliar.likeliar.common.error.SuccessCode;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {
    private final int code;
    private final String message;
    private T data;

    public static BaseResponse success(SuccessCode success){
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage());
    }

    public static <T> BaseResponse<T> success(SuccessCode success, T data){
        return new BaseResponse<T>(success.getHttpStatusCode(), success.getMessage(), data);
    }
    public static BaseResponse error(ErrorCode error){
        return new BaseResponse<>(error.getHttpStatusCode(), error.getMessage());
    }

    public static BaseResponse error(ErrorCode error, String message){
        return new BaseResponse<>(error.getHttpStatusCode(), message);
    }
}
