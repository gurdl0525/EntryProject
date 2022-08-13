package com.example.assignment.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    DUPLICATE_MEMBER(400, "Duplicate Member."),

    DUPLICATE_NICKNAME(400, "Duplicate Nickname"),

    BAD_REQUEST(400, "Bad Request."),

    ALREADY_FOLLOW(400, "Already Follow."),

    PASSWORD_INCONSISTENCY(401, "Password Not Matched."),

    UN_AUTHORIZED_TOKEN_EXCEPTION(401, "Un Authorized Token."),

    INVALID_TOKEN(401, "Invalid Token."),

    NOT_FOUND_USER(404, "Not Found User."),

    NOT_FOUND_NOVEL(404, "Not Found Novel."),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error.");

    private final Integer status;

    private final String message;

}
