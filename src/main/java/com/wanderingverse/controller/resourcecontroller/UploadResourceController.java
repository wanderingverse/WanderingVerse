package com.wanderingverse.controller.resourcecontroller;

import com.wanderingverse.common.AjaxResult;
import com.wanderingverse.service.resourceservice.UploadResourceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 资源上传
 *
 * @author WanderingVerse
 * @since 2025/08/24 14:19
 */
@Slf4j
@RestController
@RequestMapping("/upload-resource")
public class UploadResourceController {
    @Resource
    private UploadResourceService uploadResourceService;


    /**
     * 上传图片文件
     */
    @PostMapping("/image")
    public AjaxResult uploadImage(@RequestParam MultipartFile file) {
        String filePath = uploadResourceService.uploadImage(file);
        return !StringUtils.hasText(filePath) ? AjaxResult.error("图片上传失败") : AjaxResult.success(filePath);
    }
}
