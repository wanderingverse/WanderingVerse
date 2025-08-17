package com.wanderingverse.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * @author WanderingVerse
 * @since 2025/08/16 17:20
 */
public class HttpUtils {

    /**
     * 封装响应体
     */
    public static ResponseEntity<byte[]> buildResponseEntity(byte[] body, MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentLength(body.length);
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
}
