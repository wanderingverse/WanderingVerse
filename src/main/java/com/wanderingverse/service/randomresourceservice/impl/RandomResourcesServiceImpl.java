package com.wanderingverse.service.randomresourceservice.impl;

import com.wanderingverse.service.randomresourceservice.RandomResourcesService;
import com.wanderingverse.service.systemservice.WebClientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.wanderingverse.config.RandomResourcesConfig.RANDOM_IMAGE_URL_0;
import static com.wanderingverse.config.RandomResourcesConfig.RANDOM_IMAGE_URL_1;
import static com.wanderingverse.util.HttpUtils.buildResponseEntity;


/**
 * @author WanderingVerse
 * @since 2025/08/16 17:00
 **/
@Slf4j
@Service
public class RandomResourcesServiceImpl implements RandomResourcesService {
    @Resource
    private WebClientService webClientService;

    @Override
    public ResponseEntity<byte[]> getRandomImage(String width, String height) throws URISyntaxException, IOException {
        String url = UriComponentsBuilder.fromUriString(RANDOM_IMAGE_URL_0).pathSegment(width, height).build().toUriString();
        byte[] imageBytes;
        try {
            imageBytes = readImageFromUrl(url);
        } catch (IOException e) {
            log.info("图片获取失败：", e);
            url = UriComponentsBuilder.fromUriString(RANDOM_IMAGE_URL_1).build().toUriString();
            imageBytes = readImageFromUrl(url);
        }
        return buildResponseEntity(imageBytes, MediaType.IMAGE_JPEG);
    }


    private byte[] readImageFromUrl(String url) throws IOException {
        return webClientService.fetch(null, url, "GET", null, byte[].class);
    }
}
