package com.wanderingverse.service.resourceservice.impl;

import com.wanderingverse.config.MinioConfig;
import com.wanderingverse.service.resourceservice.RandomResourcesService;
import com.wanderingverse.service.systemservice.WebClientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import static com.wanderingverse.config.RandomResourcesConfig.RANDOM_IMAGE_URL_0;
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
    @Resource
    private MinioConfig minioConfig;

    @Override
    public ResponseEntity<byte[]> getRandomImage(String width, String height) {
        String url = UriComponentsBuilder.fromUriString(RANDOM_IMAGE_URL_0).pathSegment(width, height).build().toUriString();
        return buildResponseEntity(readImage(url), MediaType.IMAGE_JPEG);
    }


    private byte[] readImage(String url) {
        byte[] imageBytes = minioConfig.downloadRandomFile();
        if (imageBytes == null) {
            byte[] imageOfFetch = webClientService.fetch(null, url, "GET", null, byte[].class);
            if (imageOfFetch != null) {
                minioConfig.uploadFile(System.currentTimeMillis() + ".jpg", imageOfFetch);
                imageBytes = imageOfFetch;
            }
        }
        return imageBytes;
    }
}
