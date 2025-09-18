package com.wanderingverse.service.resourceservice;


import org.springframework.http.ResponseEntity;


/**
 * @author lihui
 * @since 2025/8/18 10:14
 */
public interface RandomResourcesService {

    /**
     * 获取随机图片
     *
     * @return ResponseEntity < byte [ ]>
     */
    ResponseEntity<byte[]> getRandomImage();

    /**
     * 获取随机文字
     *
     * @return String
     */
    String getRandomText();
}
