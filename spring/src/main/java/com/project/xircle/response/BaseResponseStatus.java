package com.project.xircle.response;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    ALREADY_EXIST_EMAIL(false,2000,"이미 존재하는 이메일입니다."),
    ALREADY_EXIST_NAME(false,2001,"이미 존재하는 별명입니다."),
    NOT_EXIST_EMAIL(false,2002,"존재하지 않는 이메일입니다."),
    NOT_EQUAL_PASSWORD(false,2003,"일치하지 않는 비밀번호입니다."),
    NOT_EXIST_USER(false,2004,"존재하지 않는 유저입니다."),
    SERVER_ERROR(false,2015,"서버 에러입니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;


    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}

