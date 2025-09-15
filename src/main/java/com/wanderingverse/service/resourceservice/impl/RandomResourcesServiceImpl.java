package com.wanderingverse.service.resourceservice.impl;

import com.wanderingverse.config.MinioConfig;
import com.wanderingverse.service.resourceservice.RandomResourcesService;
import com.wanderingverse.service.systemservice.WebClientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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
    @Resource
    private MinioConfig minioConfig;

    @Override
    public ResponseEntity<byte[]> getRandomImage() {
        String url = UriComponentsBuilder.fromUriString(RANDOM_IMAGE_URL_1).build().toUriString();
        return buildResponseEntity(readImage(), MediaType.IMAGE_JPEG);
//        return buildResponseEntity(readImage(url), MediaType.IMAGE_JPEG);
    }


    private byte[] readImage() {
        return minioConfig.downloadRandomFile();
    }

    private byte[] readImage(String url) {
        byte[] imageBytes = minioConfig.downloadRandomFile();
        byte[] imageOfFetch = webClientService.fetch(null, url, HttpMethod.GET, null, byte[].class);
        if (imageOfFetch != null) {
            minioConfig.uploadFileAsync(System.currentTimeMillis() + ".jpg", imageOfFetch);
        }
        if (imageBytes == null && imageOfFetch != null) {
            imageBytes = imageOfFetch;
        }
        return imageBytes;
    }
}
