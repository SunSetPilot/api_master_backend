package com.coderalliance.apimaster.config;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.model.vo.BaseResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return BaseResponse.error(StatusCode.BAD_REQUEST, fieldError.getDefaultMessage());
    }
}

