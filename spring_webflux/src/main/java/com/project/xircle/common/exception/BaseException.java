package com.project.xircle.common.exception;

import com.project.xircle.common.response.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private BaseResponseStatus status;
}
