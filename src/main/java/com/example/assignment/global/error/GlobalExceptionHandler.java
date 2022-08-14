package com.example.assignment.global.error;

import com.example.assignment.global.error.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<?> baseException(BaseException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> validException(){
        return ResponseEntity.status(400)
                .body("아이디는 영문 소문자 또는 숫자 4자리 이상 8자리 이하로 입력해야 합니다.");
    }
}
