package com.wanderingverse.ex;

import com.wanderingverse.util.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

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

    /**
     * 文件上传大小超限异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public AjaxResult handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.info("上传文件大小超出限制：", e);
        return AjaxResult.error(UNPROCESSABLE_ENTITY.value(), "上传文件大小超出限制");
    }

    /**
     * 前端业务校验异常
     */
    @ExceptionHandler(UnprocessableEntityException.class)
    public AjaxResult handleUnprocessableEntityException(UnprocessableEntityException e) {
        return AjaxResult.error(e.getCode(), e.getMessage());
    }


    /**
     * 后端业务异常
     *
     * @param e ServiceException
     */
    @ExceptionHandler(ServiceException.class)
    public AjaxResult handleServiceException(ServiceException e) {
        log.error("后端业务异常：", e);
        return AjaxResult.error(e.getCode(), e.getMessage());
    }


    /**
     * 默认异常
     *
     * @param e Exception
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e) {
        log.error("服务器异常：", e);
        return AjaxResult.error("服务器异常：{}", e.getMessage());
    }
}
