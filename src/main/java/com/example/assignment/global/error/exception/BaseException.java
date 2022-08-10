package com.example.assignment.global.error.exception;

import com.example.assignment.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode){
        super();
        this.errorCode = errorCode;
    }

}
