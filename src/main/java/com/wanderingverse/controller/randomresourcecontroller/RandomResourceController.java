package com.wanderingverse.controller.randomresourcecontroller;


import com.wanderingverse.common.AjaxResult;
import com.wanderingverse.service.randomresourceservice.RandomResourcesService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * 随机资源获取
 *
 * @author WanderingVerse
 * @since 2025/08/16 16:47
 **/
@Slf4j
@RestController
@RequestMapping("/random")
public class RandomResourceController {
    @Resource
    private RandomResourcesService randomResourcesService;


    /**
     * 获取随机图片
     */
    @GetMapping("/image")
    public AjaxResult getRandomImage(@RequestParam(defaultValue = "260") String width, @RequestParam(defaultValue = "160") String height) throws IOException, URISyntaxException {
        ResponseEntity<byte[]> responseEntity = randomResourcesService.getRandomImage(width, height);
        if (ObjectUtils.isEmpty(responseEntity)) {
            return AjaxResult.error("随机图片获取失败");
        }
        return AjaxResult.success("获取随机图片", responseEntity);
    }
}
