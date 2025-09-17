package com.wanderingverse.ex;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Service 后端业务异常
 *
 * @author lihui
 * @since 2025/03/27 10:01
 **/
@Getter
public class ServiceException extends RuntimeException {
    private final Integer code;
    private final String message;

    public ServiceException(String message) {
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
