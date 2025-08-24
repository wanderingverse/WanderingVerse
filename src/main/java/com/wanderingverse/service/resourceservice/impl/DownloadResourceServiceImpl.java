package com.wanderingverse.service.resourceservice.impl;

import com.wanderingverse.config.MinioConfig;
import com.wanderingverse.service.resourceservice.DownloadResourceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author WanderingVerse
 * @since 2025/08/24 16:14
 */
@Slf4j
@Service
public class DownloadResourceServiceImpl implements DownloadResourceService {
    @Resource
    private MinioConfig minioConfig;

    @Override
    public ResponseEntity<byte[]> downloadImage(String filename) {
        byte[] downloadFile = minioConfig.downloadFile(filename);
        return ResponseEntity.ok().body(downloadFile);
    }

    @Override
    public String getImagePreSignedUrl(String filename) {
        return minioConfig.getPreSignedUrl(filename, null);
    }
}
