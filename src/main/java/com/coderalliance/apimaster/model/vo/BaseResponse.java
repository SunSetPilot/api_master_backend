package com.coderalliance.apimaster.model.vo;

import com.coderalliance.apimaster.constant.StatusCode;
import lombok.Data;

@Data
public class BaseResponse<T> {
    private final int status;
    private final String message;
    private final T data;

    public BaseResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), "success", null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), "success", data);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(StatusCode.ERROR.getCode(), message, null);
    }

    public static <T> BaseResponse<T> error(StatusCode status, String message) {
        return new BaseResponse<>(status.getCode(), message, null);
    }
}
