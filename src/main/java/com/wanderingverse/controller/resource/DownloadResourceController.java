package com.wanderingverse.controller.resource;

import com.wanderingverse.service.resource.DownloadResourceService;
import com.wanderingverse.service.resource.RandomResourcesService;
import com.wanderingverse.util.AjaxResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 资源获取
 *
 * @author WanderingVerse
 * @since 2025/08/24 16:12
 */
@Slf4j
@RestController
@RequestMapping("/download-resource")
public class DownloadResourceController {
    @Resource
    private DownloadResourceService downloadResourceService;
    @Resource
    private RandomResourcesService randomResourcesService;


    /**
     * 下载指定文件名的图片
     */
    @GetMapping("/image/{filename}")
    public AjaxResult downloadImage(@PathVariable String filename) {
        ResponseEntity<byte[]> responseEntity = downloadResourceService.downloadImage(filename);
        return AjaxResult.success(responseEntity);
    }

    /**
     * 获取指定图片的预签名 URL
     */
    @GetMapping("/image/pre-signed-url/{filename}")
    public AjaxResult getImagePreSignedUrl(@PathVariable String filename) {
        String preSignedUrl = downloadResourceService.getImagePreSignedUrl(filename);
        return StringUtils.hasText(preSignedUrl) ? AjaxResult.success(preSignedUrl) : AjaxResult.error("文件不存在");
    }

    /**
     * 获取随机图片
     */
    @GetMapping("/random/image")
    public AjaxResult getRandomImage() {
        ResponseEntity<byte[]> responseEntity = randomResourcesService.getRandomImage();
        return AjaxResult.success(responseEntity);
    }

    /**
     * 获取随机图片 URL
     */
    @GetMapping("/random/image/url")
    public AjaxResult getRandomImagePreSignedUrl() {
        String preSignedUrl = randomResourcesService.getRandomImagePreSignedUrl();
        return AjaxResult.success(preSignedUrl);
    }

    /**
     * 获取随机一段文字
     */
    @GetMapping("/random/text")
    public AjaxResult getRandomText(@RequestParam(required = false) String randomTextType) {
        String randomText = randomResourcesService.getRandomText(randomTextType);
        return AjaxResult.success(randomText);
    }
}
