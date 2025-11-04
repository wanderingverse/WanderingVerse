package com.wanderingverse.service.resource.impl;

import com.wanderingverse.config.MinioConfig;
import com.wanderingverse.service.resource.UploadResourceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.wanderingverse.util.FileUtils.generateUniqueFileName;

/**
 * @author WanderingVerse
 * @since 2025/08/24 14:22
 */
@Slf4j
@Service
public class UploadResourceServiceImpl implements UploadResourceService {
    @Resource
    private MinioConfig minioConfig;

    @Override
    public String uploadImage(MultipartFile file) {
        String fileName = generateUniqueFileName(file);
        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (Exception e) {
            log.error("图片上传失败：", e);
        }
        boolean result = minioConfig.uploadFile(fileName, bytes);
        return result ? fileName : "";
    }
}
