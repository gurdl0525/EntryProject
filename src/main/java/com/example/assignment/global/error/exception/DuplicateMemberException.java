package com.example.assignment.global.error.exception;

import com.example.assignment.global.error.ErrorCode;

public class DuplicateMemberException extends BaseException{

    private DuplicateMemberException(){
        super(ErrorCode.DUPLICATE_MEMBER);
    }

    private static class SingletonHelper{
        private static final DuplicateMemberException INSTANCE = new DuplicateMemberException();
    }

    public static  DuplicateMemberException getInstance(){
        return SingletonHelper.INSTANCE;
    }

}
