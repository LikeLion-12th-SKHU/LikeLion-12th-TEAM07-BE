package com.likeliar.likeliar.common.exception;

import com.likeliar.likeliar.common.error.ErrorCode;

public class NotFoundException extends CustomException {

    public NotFoundException(ErrorCode errorcode, String message){
        super(errorcode, message);
    }
}
