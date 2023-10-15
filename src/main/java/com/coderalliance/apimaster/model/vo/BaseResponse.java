package com.coderalliance.apimaster.model.vo;

import com.coderalliance.apimaster.constant.StatusCode;
import lombok.Data;

@Data
public class BaseResponse {
    private int status;
    private String message;
    private Object data;

    public BaseResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static BaseResponse success() {
        return new BaseResponse(StatusCode.SUCCESS.getCode(), "success", null);
    }

    public static BaseResponse success(Object data) {
        return new BaseResponse(StatusCode.SUCCESS.getCode(), "success", data);
    }

    public static BaseResponse error(String message) {
        return new BaseResponse(StatusCode.ERROR.getCode(), message, null);
    }

    public static BaseResponse error(StatusCode status, String message) {
        return new BaseResponse(status.getCode(), message, null);
    }
}
