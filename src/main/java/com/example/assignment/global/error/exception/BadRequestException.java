package com.example.assignment.global.error.exception;

import com.example.assignment.global.error.ErrorCode;

public class BadRequestException extends BaseException{

    private BadRequestException(){
        super(ErrorCode.BAD_REQUEST);
    }

    private static class SingletonHelper{
        private static final BadRequestException INSTANCE = new BadRequestException();
    }

    public static BadRequestException getInstance(){
        return SingletonHelper.INSTANCE;
    }

}
