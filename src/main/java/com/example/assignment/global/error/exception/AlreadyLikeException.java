package com.example.assignment.global.error.exception;

import com.example.assignment.global.error.ErrorCode;

public class AlreadyLikeException extends BaseException{

    private AlreadyLikeException(){
        super(ErrorCode.ALREADY_FOLLOW);
    }

    private static class SingletonHelper{
        private static final AlreadyLikeException INSTANCE = new AlreadyLikeException();
    }

    public static AlreadyLikeException getInstance(){
        return SingletonHelper.INSTANCE;
    }

}
