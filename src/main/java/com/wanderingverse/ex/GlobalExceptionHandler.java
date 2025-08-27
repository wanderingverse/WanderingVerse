package com.wanderingverse.ex;

import com.wanderingverse.common.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * @author lihui
 * @since 2025/8/27 14:53
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求参数校验异常
     *
     * @param e MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> objectErrorList = e.getBindingResult().getAllErrors();
        String message = objectErrorList.get(0).getDefaultMessage();
        log.info("请求参数校验异常：", e);
        return AjaxResult.error(UNPROCESSABLE_ENTITY.value(), message);
    }

    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e) {
        log.error("服务器异常：", e);
        return AjaxResult.error("服务器异常");
    }
}
