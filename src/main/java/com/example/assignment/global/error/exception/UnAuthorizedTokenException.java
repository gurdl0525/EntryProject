package com.example.assignment.global.error.exception;

import com.example.assignment.global.error.ErrorCode;

public class UnAuthorizedTokenException extends BaseException {
    private UnAuthorizedTokenException(){
        super(ErrorCode.UN_AUTHORIZED_TOKEN_EXCEPTION);
    }

    private static class SingletonHelper{
        private static final UnAuthorizedTokenException INSTANCE = new UnAuthorizedTokenException();
    }

    public static UnAuthorizedTokenException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
