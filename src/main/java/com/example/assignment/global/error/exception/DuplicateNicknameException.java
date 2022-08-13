package com.example.assignment.global.error.exception;

import com.example.assignment.global.error.ErrorCode;

public class DuplicateNicknameException extends BaseException{

    private DuplicateNicknameException(){
        super(ErrorCode.DUPLICATE_NICKNAME);
    }

    private static class SingletonHelper{
        private static final DuplicateNicknameException INSTANCE = new DuplicateNicknameException();
    }

    public static DuplicateNicknameException getInstance(){
        return SingletonHelper.INSTANCE;
    }

}
