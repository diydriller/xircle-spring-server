package com.project.xircle.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.io.Serializable;

import static com.project.xircle.common.response.BaseResponseStatus.SUCCESS;

@Data
@AllArgsConstructor
@Builder
public class BaseResponse<T> {

    private final Boolean success;
    private final String message;
    private final int code;
    private T data;


    // 요청에 성공한 경우
    public BaseResponse(T data) {
        this.success = SUCCESS.isSuccess();
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
        this.data = data;
    }

    // 요청에 실패한 경우
    public BaseResponse(BaseResponseStatus status) {
        this.success = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }

}
