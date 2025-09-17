package com.wanderingverse.ex;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 前端业务校验异常
 *
 * @author lihui
 * @since 2025/9/17 11:50
 **/
@Getter
public class UnprocessableEntityException extends RuntimeException {
    private final Integer code;
    private final String message;

    public UnprocessableEntityException(String message) {
        this.code = HttpStatus.UNPROCESSABLE_ENTITY.value();
        this.message = message;
    }
}
