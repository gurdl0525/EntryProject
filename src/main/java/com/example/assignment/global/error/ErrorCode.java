package com.example.assignment.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    UN_AUTHORIZED_TOKEN_EXCEPTION(401, "Un Authorized Token."),
    INVALID_TOKEN(401, "Invalid Token."),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error.");

    private final Integer status;

    private final String message;

}
