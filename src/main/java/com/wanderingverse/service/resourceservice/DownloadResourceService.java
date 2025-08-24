package com.wanderingverse.service.resourceservice;


import org.springframework.http.ResponseEntity;

/**
 * @author WanderingVerse
 * @since 2025/08/24 16:14
 **/
public interface DownloadResourceService {

    /**
     * 下载图片
     *
     * @param filename 文件名
     * @return ResponseEntity<byte [ ]>
     */
    ResponseEntity<byte[]> downloadImage(String filename);


    /**
     * 获取指定图片的预签名 URL
     *
     * @param filename 文件名
     * @return URL
     */
    String getImagePreSignedUrl(String filename);
}
