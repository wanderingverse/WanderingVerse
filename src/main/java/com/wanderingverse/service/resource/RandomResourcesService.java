package com.wanderingverse.service.resource;


import com.wanderingverse.common.RandomTextTypeEnum;
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
     * 获取随机图片 URL
     *
     * @return String
     */
    String getRandomImagePreSignedUrl();

    /**
     * 获取随机文字
     *
     * @param randomTextType 文字类型
     * @return String
     * @see RandomTextTypeEnum
     */
    String getRandomText(String randomTextType);
}
