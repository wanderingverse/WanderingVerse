package com.wanderingverse.service.randomresourceservice;


import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URISyntaxException;

public interface RandomResourcesService {

    /**
     * 获取随机图片
     *
     * @param width  宽度
     * @param height 高度
     * @return ResponseEntity<byte [ ]>
     * @throws URISyntaxException URISyntaxException
     * @throws IOException        IOException
     */
    ResponseEntity<byte[]> getRandomImage(String width, String height) throws URISyntaxException, IOException;
}
