package com.coderalliance.apimaster.config;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.model.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public BaseResponse<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return BaseResponse.error(StatusCode.BAD_REQUEST, "parameter " + ex.getParameterName() + " required");
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return BaseResponse.error(StatusCode.BAD_REQUEST, fieldError.getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse<String> handleException(Exception e) {
        log.error("api error: ", e);
        return BaseResponse.error(StatusCode.ERROR, e.getMessage());
    }
}

