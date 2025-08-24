package com.wanderingverse.service.resourceservice;


import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;


/**
 * @author lihui
 * @since 2025/8/18 10:14
 */
public interface RandomResourcesService {

    /**
     * 获取随机图片
     *
     * @param width  宽度
     * @param height 高度
     * @return Mono<ResponseEntity < byte [ ]>>
     */
    Mono<ResponseEntity<byte[]>> getRandomImage(String width, String height);
}
