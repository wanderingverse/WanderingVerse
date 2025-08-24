package com.wanderingverse.service.resourceservice;


import org.springframework.web.multipart.MultipartFile;

/**
 * @author WanderingVerse
 * @since 2025/08/24 14:23
 **/
public interface UploadResourceService {


    /**
     * 图片文件上传
     *
     * @param file 图片文件
     * @return 上传后的文件路径
     */
    String uploadImage(MultipartFile file);
}
