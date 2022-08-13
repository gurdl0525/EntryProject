package com.example.assignment.global.error.exception;

import com.example.assignment.global.error.ErrorCode;

public class NotFoundNovelException extends BaseException{

    private NotFoundNovelException(){
        super(ErrorCode.NOT_FOUND_NOVEL);
    }

    private static class SingletonHelper{
        private static final NotFoundNovelException INSTANCE = new NotFoundNovelException();
    }

    public static NotFoundNovelException getInstance(){
        return SingletonHelper.INSTANCE;
    }

}
