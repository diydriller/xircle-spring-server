package com.project.xircle.common.response;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    NOT_EXIST_EMAIL(false,2001,"존재하지 않는 이메일입니다."),
    ALREADY_EXIST_EMAIL(false,2002,"이미 존재하는 이메일입니다."),
    NOT_EQUAL_CODE(false,2003,"인증번호가 틀립니다."),
    ALREADY_EXIST_NAME(false,2004,"이미 존재하는 별명입니다."),
    FILE_UPLOAD_ERROR(false,2005,"파일 업로드 에러입니다."),
    NOT_EXIST_USER(false,2006,"존재하지 않는 유저입니다"),
    NOT_EQUAL_PASSWORD(false,2007,"비밀번호가 틀립니다.");


    private final boolean success;
    private final int code;
    private final String message;


    BaseResponseStatus(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
