package com.wanderingverse.controller.randomresourcecontroller;


import com.wanderingverse.common.AjaxResult;
import com.wanderingverse.service.randomresourceservice.RandomResourcesService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


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
    public Mono<AjaxResult> getRandomImage(@RequestParam(defaultValue = "260") String width, @RequestParam(defaultValue = "160") String height) {
        Mono<ResponseEntity<byte[]>> responseEntityMono = randomResourcesService.getRandomImage(width, height);
        return responseEntityMono.map(responseEntity -> AjaxResult.success("获取随机图片", responseEntity)).defaultIfEmpty(AjaxResult.error("随机图片获取失败"));
    }
}
