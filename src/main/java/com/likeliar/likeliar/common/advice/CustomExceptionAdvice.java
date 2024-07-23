package com.likeliar.likeliar.common.advice;

import com.likeliar.likeliar.common.dto.BaseResponse;
import com.likeliar.likeliar.common.error.ErrorCode;
import com.likeliar.likeliar.common.exception.CustomException;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@Component
@RequiredArgsConstructor
public class CustomExceptionAdvice {

   //500 Internal Server Error -> 원인 모를 이유의 예외가 발생 시

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse handlerServerException(final Exception e){
        log.error("Internal Server Error: {}", e.getMessage(), e);
        return BaseResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    // Custom error -> 내부 커스텀 에러, 예외 처리하기
    @ExceptionHandler(CustomException.class)
    public BaseResponse handlerCustomException(CustomException e){
        log.error("Custom Exception: {}", e.getMessage(), e);
        return  BaseResponse.error(e.getErrorCode(), e.getMessage());
    }

    //400 BAD REQUET
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Map<String, String>> handleValidationException(MethodArgumentNotValidException e){
        //에러 메세지 생성
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()){
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        // 응답 생성
        return BaseResponse.error(ErrorCode.VALIDATION_ERROR, convertMapToString(errorMap));
    }

    private String convertMapToString(Map<String, String> map){
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, String> entry : map.entrySet()){
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append(", ");
        }

        sb.deleteCharAt(sb.length() - 1 );
        sb.deleteCharAt(sb.length() - 1 );
        return sb.toString();
    }

}
