package com.project.xircle.error;

import com.project.xircle.response.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private BaseResponseStatus status;
}
